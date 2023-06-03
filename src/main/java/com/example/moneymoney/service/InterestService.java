package com.example.moneymoney.service;

import com.example.moneymoney.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface InterestService {
    List<BigDecimal> getAllInterestRates(  );
    List<Integer> getAllTermMonths( );
    BigDecimal calculateExpectedInterest(BigDecimal currentAmount, List<BigDecimal> interestRates, List<Integer> termMonths);
}
