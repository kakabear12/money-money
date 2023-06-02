package com.example.moneymoney.repository;

import com.example.moneymoney.entity.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeCategoryRepository extends JpaRepository<IncomeCategory, Long> {
    @Query("SELECT ic FROM IncomeCategory ic WHERE ic.incomeCategoryName = :incomeCategoryName")
    IncomeCategory findByIncomeCategoryName(String incomeCategoryName);
}
