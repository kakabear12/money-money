package com.example.moneymoney.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "interest_term")
public class InterestTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Column(name = "interest_rate")
    private BigDecimal interestRate; // Lãi suất

    @Column(name="term_months") // Kỳ hạn
    private int termMonths;

    @OneToMany(mappedBy = "interestTerm", cascade = CascadeType.ALL)
    private List<SavingsGoal> savingsGoals;

    // getters and setters
}