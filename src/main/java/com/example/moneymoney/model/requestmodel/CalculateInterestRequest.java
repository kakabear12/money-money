package com.example.moneymoney.model.requestmodel;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CalculateInterestRequest {
    private BigDecimal currentAmount;
    private List<BigDecimal> interestRate;
    private List<Integer> termMonths;
}
