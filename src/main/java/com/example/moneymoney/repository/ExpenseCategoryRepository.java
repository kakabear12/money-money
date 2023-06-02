package com.example.moneymoney.repository;

import com.example.moneymoney.entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    ExpenseCategory findByExpenseCategoryName(String expenseCategoryName);
}