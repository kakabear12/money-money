package com.example.moneymoney.repository;

import com.example.moneymoney.entity.InterestTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InterestTermRepository extends JpaRepository<InterestTerm, Long> {

    @Query("SELECT DISTINCT i.interestRate FROM InterestTerm i")
    List<BigDecimal> findAllInterestRates();

    @Query("SELECT DISTINCT i.termMonths FROM InterestTerm i")
    List<Integer> findAllTermMonths();
}
