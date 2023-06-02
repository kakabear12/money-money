package com.example.moneymoney.controller;

import com.example.moneymoney.entity.*;
import com.example.moneymoney.model.requestmodel.IncomeRequestModel;
import com.example.moneymoney.model.responsemodel.ExpenseResponse;
import com.example.moneymoney.model.responsemodel.IncomeResponse;
import com.example.moneymoney.service.IncomeService;
import com.example.moneymoney.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/money-money/users/incomes")
@Slf4j
@RequiredArgsConstructor
public class IncomeController {

    private final IncomeService incomeService;

    private final UserService userService;



    @PostMapping
    public ResponseEntity<IncomeResponse> createIncome(@RequestBody IncomeRequestModel incomeRequest, Principal principal) {
        String username = principal.getName();
        User loggedInUser = userService.findUserByEmail(username);
        IncomeResponse createdIncome = incomeService.createIncome(incomeRequest, loggedInUser);
        return ResponseEntity.ok(createdIncome);
    }

    @GetMapping("/{incomeId}")
    public ResponseEntity<IncomeResponse> getIncomeById(@PathVariable Long incomeId) {
        IncomeResponse incomeResponse = incomeService.getIncomeById(incomeId);
        return ResponseEntity.ok(incomeResponse);
    }

    @PutMapping("/{incomeId}")
    public ResponseEntity<IncomeResponse> updateIncome(@PathVariable Long incomeId, @RequestBody IncomeRequestModel incomeRequest) {
        IncomeResponse updatedIncome = incomeService.updateIncome(incomeId, incomeRequest);
        return ResponseEntity.ok(updatedIncome);
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long incomeId) {
        incomeService.deleteIncome(incomeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<IncomeResponse>> getListIncome() {
        List<Income> incomes = incomeService.getListIncome();
        List<IncomeResponse> expenseResponses = incomes.stream()
                .map(this::mapToIncomeResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(expenseResponses);
    }


    private IncomeResponse mapToIncomeResponse(Income income) {
        IncomeResponse incomeResponse = new IncomeResponse();
        incomeResponse.setIncomeCategoryName(income.getIncomeCategory().getIncomeCategoryName());
        incomeResponse.setDate(income.getDate());
        incomeResponse.setAmount(income.getAmount());
        incomeResponse.setDescription(income.getDescription());
        incomeResponse.setAssetName(income.getAsset().getAssetName());
        return incomeResponse;
    }

    @GetMapping("/total-by-day")
    public ResponseEntity<BigDecimal> getTotalAmountByDay(@RequestParam("date") Date date, Principal principal) {
        String username = principal.getName();
        User loggedInUser = userService.findUserByEmail(username);
        BigDecimal totalAmount = incomeService.getTotalAmountByDay(date, loggedInUser);
        return ResponseEntity.ok(totalAmount);
    }

    @GetMapping("/total-by-week")
    public ResponseEntity<BigDecimal> getTotalAmountByWeek(@RequestParam("date") Date date, Principal principal) {
        String userName = principal.getName();
        User loggedInUser = userService.findUserByEmail(userName);
        BigDecimal totalAmount = incomeService.getTotalAmountByWeek(date, loggedInUser);
        return ResponseEntity.ok(totalAmount);
    }

    @GetMapping("/total-by-month")
    public ResponseEntity<BigDecimal> getTotalAmountByMonth(@RequestParam("date") Date date, Principal principal) {
        String userName = principal.getName();
        User loggedInUser = userService.findUserByEmail(userName);
        BigDecimal totalAmount = incomeService.getTotalAmountByMonth(date, loggedInUser);
        return ResponseEntity.ok(totalAmount);
    }

    @GetMapping("/total-by-year")
    public ResponseEntity<BigDecimal> getTotalAmountByYear(@RequestParam("year") int year, Principal principal) {
        String userName = principal.getName();
        User loggedInUser = userService.findUserByEmail(userName);
        BigDecimal totalAmount = incomeService.getTotalAmountByYear(year, loggedInUser);
        return ResponseEntity.ok(totalAmount);
    }
}