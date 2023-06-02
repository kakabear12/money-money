package com.example.moneymoney.service;

import com.example.moneymoney.entity.User;

import java.math.BigDecimal;
import java.util.Date;

public interface ProfitService {
    BigDecimal getProfitByDay(Date date, User loggedInUser );
    BigDecimal getProfitByWeek(Date date, User loggedInUser);
    BigDecimal getProfitByMonth(Date date, User loggedInUser);
    BigDecimal getProfitByYear(int year, User loggedInUser);
}
