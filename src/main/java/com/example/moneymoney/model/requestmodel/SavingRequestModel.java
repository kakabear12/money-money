package com.example.moneymoney.model.requestmodel;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SavingRequestModel {

    private String goalName;
    private BigDecimal currentAmount;
    private BigDecimal interestRate;
    private int termMonths;
    private BigDecimal expectedInterest;
}
