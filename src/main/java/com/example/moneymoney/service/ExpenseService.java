package com.example.moneymoney.service;

import com.example.moneymoney.entity.Expense;
import com.example.moneymoney.entity.User;
import com.example.moneymoney.model.requestmodel.ExpenseRequestModel;
import com.example.moneymoney.model.responsemodel.ExpenseResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ExpenseService {
    ExpenseResponse  createExpense(ExpenseRequestModel expenseRequest, User loggedInUser);

    ExpenseResponse getExpenseById(Long expenseId);

    void deleteExpense(Long expenseId);

    ExpenseResponse updateExpense(Long expenseId, ExpenseRequestModel expenseRequest);

    List<Expense> getListExpense();

    BigDecimal getTotalAmountByDay(Date date, User loggedInUser);

    BigDecimal getTotalAmountByWeek(Date date, User loggedInUser);
    public BigDecimal getTotalAmountByMonth(Date date, User loggedInUser);

    public BigDecimal getTotalAmountByYear(int year, User loggedInUser);

}
