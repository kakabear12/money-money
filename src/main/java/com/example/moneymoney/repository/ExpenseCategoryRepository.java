package com.example.moneymoney.repository;

import com.example.moneymoney.entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    ExpenseCategory findByExpenseCategoryName(String expenseCategoryName);

//    @Query("SELECT ec FROM ExpenseCategory ec")
//    List<ExpenseCategory> getAllExpenseCategories();
}