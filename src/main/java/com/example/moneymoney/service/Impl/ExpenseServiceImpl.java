package com.example.moneymoney.service.Impl;

import com.example.moneymoney.entity.*;
import com.example.moneymoney.model.requestmodel.ExpenseRequestModel;
import com.example.moneymoney.model.responsemodel.ExpenseResponse;
import com.example.moneymoney.repository.AssetRepository;
import com.example.moneymoney.repository.ExpenseCategoryRepository;
import com.example.moneymoney.repository.ExpenseRepository;
import com.example.moneymoney.service.ExpenseService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final AssetRepository assetRepository;


    @Override
    public ExpenseResponse createExpense(ExpenseRequestModel expenseRequest, User loggedInUser) {
        String expenseCategoryName = expenseRequest.getExpenseCategoryName();
        String assetName = expenseRequest.getAssetName();

        ExpenseCategory expenseCategory = expenseCategoryRepository.findByExpenseCategoryName(expenseCategoryName);
        if (expenseCategory == null) {
            throw new IllegalArgumentException("Expense category not found");
        }

        Asset asset = assetRepository.findByAssetName(assetName);
        if (asset == null) {
            throw new IllegalArgumentException("Asset not found");
        }

        Expense expense = new Expense();
        expense.setUser(loggedInUser);
        expense.setExpenseCategory(expenseCategory);
        expense.setDate(expenseRequest.getDate());
        expense.setAmount(expenseRequest.getAmount());
        expense.setDescription(expenseRequest.getDescription());
        expense.setAsset(asset);

        Expense createdExpense = expenseRepository.save(expense);

        ExpenseResponse expenseResponse = new ExpenseResponse();
        expenseResponse.setExpenseCategoryName(createdExpense.getExpenseCategory().getExpenseCategoryName());
        expenseResponse.setDate(createdExpense.getDate());
        expenseResponse.setAmount(createdExpense.getAmount());
        expenseResponse.setDescription(createdExpense.getDescription());
        expenseResponse.setAssetName(createdExpense.getAsset().getAssetName());

        return expenseResponse;
    }

    @Override
    public ExpenseResponse getExpenseById(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        ExpenseResponse expenseResponse = new ExpenseResponse();
        expenseResponse.setExpenseCategoryName(expense.getExpenseCategory().getExpenseCategoryName());
        expenseResponse.setDate(expense.getDate());
        expenseResponse.setAmount(expense.getAmount());
        expenseResponse.setDescription(expense.getDescription());

        Asset asset = expense.getAsset();
        if (asset != null) {
            expenseResponse.setAssetName(asset.getAssetName());
        }

        return expenseResponse;
    }

    @Override
    public void deleteExpense(Long expenseId) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expenseRepository.delete(expense);
    }


    @Override
    public ExpenseResponse updateExpense(Long expenseId, ExpenseRequestModel expenseRequest) {
        Expense existingExpense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        ExpenseCategory expenseCategory = expenseCategoryRepository.findByExpenseCategoryName(expenseRequest.getExpenseCategoryName());
        if (expenseCategory == null) {
            throw new IllegalArgumentException("Expense category not found");
        }

        existingExpense.setExpenseCategory(expenseCategory);
        existingExpense.setDate(expenseRequest.getDate());
        existingExpense.setAmount(expenseRequest.getAmount());
        existingExpense.setDescription(expenseRequest.getDescription());

        Asset asset = existingExpense.getAsset();
        if (asset == null) {
            asset = new Asset();
            existingExpense.setAsset(asset);
        }
        asset.setAssetName(expenseRequest.getAssetName());

        Expense updatedExpense = expenseRepository.save(existingExpense);

        ExpenseResponse expenseResponse = new ExpenseResponse();
        expenseResponse.setExpenseCategoryName(updatedExpense.getExpenseCategory().getExpenseCategoryName());
        expenseResponse.setDate(updatedExpense.getDate());
        expenseResponse.setAmount(updatedExpense.getAmount());
        expenseResponse.setDescription(updatedExpense.getDescription());
        expenseResponse.setAssetName(updatedExpense.getAsset().getAssetName());

        return expenseResponse;
    }


    @Override
    public List<Expense> getListExpense() {
        return expenseRepository.findAll();
    }


    @Override
    public BigDecimal getTotalAmountByDay(Date date, User loggedInUser) {
        return expenseRepository.getTotalAmountByDay(date,loggedInUser);
    }

    @Override
    public BigDecimal getTotalAmountByWeek(Date date, User loggedInUser) {
        return expenseRepository.getTotalAmountByWeek(date,loggedInUser);
    }

    @Override
    public BigDecimal getTotalAmountByMonth(Date date, User loggedInUser) {
        return expenseRepository.getTotalAmountByMonth(date,loggedInUser);
    }

    @Override
    public BigDecimal getTotalAmountByYear(int year, User loggedInUser) {
        return expenseRepository.getTotalAmountByYear(year,loggedInUser);
    }
}
