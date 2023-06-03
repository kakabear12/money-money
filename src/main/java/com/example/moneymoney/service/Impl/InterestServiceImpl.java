package com.example.moneymoney.service.Impl;

import com.example.moneymoney.repository.InterestTermRepository;
import com.example.moneymoney.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService {

    private final InterestTermRepository interestTermRepository;

    @Override
    public List<BigDecimal> getAllInterestRates() {
        return interestTermRepository.findAllInterestRates();
    }

    @Override
    public List<Integer> getAllTermMonths() {
        return interestTermRepository.findAllTermMonths();
    }

    @Override
    public BigDecimal calculateExpectedInterest(BigDecimal currentAmount, List<BigDecimal> interestRates, List<Integer> termMonths) {
        // Kiểm tra xem interestRates và termMonths đã được chọn trong danh sách hay chưa
        if (!interestRates.containsAll(interestRates) || !termMonths.containsAll(termMonths)) {
            throw new IllegalArgumentException("Invalid interest rates or term months.");
        }

        BigDecimal totalExpectedInterest = BigDecimal.ZERO;
        for (int i = 0; i < interestRates.size(); i++) {
            BigDecimal interestRate = interestRates.get(i);
            int termMonth = termMonths.get(i);

            BigDecimal interestPercentage = interestRate.divide(BigDecimal.valueOf(100));
            BigDecimal monthlyInterest = currentAmount.multiply(interestPercentage).divide(BigDecimal.valueOf(12), 2, RoundingMode.HALF_UP);
            BigDecimal expectedInterest = monthlyInterest.multiply(BigDecimal.valueOf(termMonth));

            totalExpectedInterest = totalExpectedInterest.add(expectedInterest);
        }

        return totalExpectedInterest;
    }
}
