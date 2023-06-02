package com.example.moneymoney.service.Impl;

import com.example.moneymoney.entity.User;
import com.example.moneymoney.repository.ExpenseRepository;
import com.example.moneymoney.repository.IncomeRepository;

import com.example.moneymoney.service.ProfitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProfitServiceImpl implements ProfitService {
    private final IncomeRepository incomeRepository;
    private final ExpenseRepository  expenseRepository;



    @Override
    public BigDecimal getProfitByDay(Date date, User loggedInUser) {
        BigDecimal totalIncome = incomeRepository.getTotalAmountByDay(date, loggedInUser);
        BigDecimal totalExpense = expenseRepository.getTotalAmountByDay(date, loggedInUser);
        return totalIncome.subtract(totalExpense);
    }

    @Override
    public BigDecimal getProfitByWeek(Date date, User loggedInUser) {
        BigDecimal totalIncome = incomeRepository.getTotalAmountByWeek(date, loggedInUser);
        BigDecimal totalExpense = expenseRepository.getTotalAmountByWeek(date, loggedInUser);
        return totalIncome.subtract(totalExpense);
    }

    @Override
    public BigDecimal getProfitByMonth(Date date, User loggedInUser) {
        BigDecimal totalIncome = incomeRepository.getTotalAmountByMonth(date, loggedInUser);
        BigDecimal totalExpense = expenseRepository.getTotalAmountByMonth(date, loggedInUser);
        return totalIncome.subtract(totalExpense);
    }

    @Override
    public BigDecimal getProfitByYear(int year, User loggedInUser) {
        BigDecimal totalIncome = incomeRepository.getTotalAmountByYear(year, loggedInUser);
        BigDecimal totalExpense = expenseRepository.getTotalAmountByYear(year, loggedInUser);
        return totalIncome.subtract(totalExpense);
    }
}
