package com.example.moneymoney.service.Impl;

import com.example.moneymoney.entity.ExpenseCategory;
import com.example.moneymoney.repository.ExpenseCategoryRepository;
import com.example.moneymoney.service.ExpenseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

    private  final ExpenseCategoryRepository expenseCategoryRepository;
    @Override
    public List<ExpenseCategory> getAllExpenseCategories() {
        return expenseCategoryRepository.findAll();
    }
}
