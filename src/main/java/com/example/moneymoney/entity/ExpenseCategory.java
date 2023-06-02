package com.example.moneymoney.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "expense_category")
public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_category_id")
    private Long id;


    @Column(name = "category_name")
    private String expenseCategoryName;

    public ExpenseCategory(String expenseCategoryName) {
        this.expenseCategoryName = expenseCategoryName;
    }
}
