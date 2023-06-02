package com.example.moneymoney.service;

import com.example.moneymoney.entity.Expense;
import com.example.moneymoney.entity.Income;
import com.example.moneymoney.entity.User;
import com.example.moneymoney.model.requestmodel.ExpenseRequestModel;
import com.example.moneymoney.model.requestmodel.IncomeRequestModel;
import com.example.moneymoney.model.responsemodel.ExpenseResponse;
import com.example.moneymoney.model.responsemodel.IncomeResponse;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface IncomeService {

    IncomeResponse createIncome(IncomeRequestModel incomeRequest, User loggedInUser);
    IncomeResponse getIncomeById(Long incomeId);
    IncomeResponse updateIncome(Long incomeId, IncomeRequestModel incomeRequest);

    void deleteIncome(Long incomeId);

    List<Income> getListIncome();


    BigDecimal getTotalAmountByDay(Date date, User loggedInUser);

    BigDecimal getTotalAmountByWeek(Date date, User loggedInUser);

    BigDecimal getTotalAmountByMonth(Date date, User loggedInUser);

    BigDecimal getTotalAmountByYear(int year, User loggedInUser);
}
