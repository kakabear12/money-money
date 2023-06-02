package com.example.moneymoney.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "income_category")
public class IncomeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_category_id")
    private Long id;

    @Column(name = "income_category_name")
    private String incomeCategoryName;


    public IncomeCategory(String incomeCategoryName) {
        this.incomeCategoryName = incomeCategoryName;
    }
}

