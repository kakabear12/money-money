package com.example.moneymoney.controller;

import com.example.moneymoney.entity.Expense;

import com.example.moneymoney.entity.User;
import com.example.moneymoney.model.requestmodel.ExpenseRequestModel;
import com.example.moneymoney.model.responsemodel.ExpenseResponse;
import com.example.moneymoney.service.ExpenseService;
import com.example.moneymoney.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/money-money/users/expenses")
@Slf4j
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody ExpenseRequestModel expenseRequest, Principal principal) {
        String username = principal.getName();
        User loggedInUser = userService.findUserByEmail(username);
        ExpenseResponse createdExpense = expenseService.createExpense(expenseRequest, loggedInUser);
        return ResponseEntity.ok(createdExpense);
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> updateExpense(@PathVariable Long expenseId, @RequestBody ExpenseRequestModel expenseRequest) {
        ExpenseResponse updatedExpense = expenseService.updateExpense(expenseId, expenseRequest);
        return ResponseEntity.ok(updatedExpense);
    }


    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> getExpenseById(@PathVariable Long expenseId) {
        ExpenseResponse expenseResponse = expenseService.getExpenseById(expenseId);
        return ResponseEntity.ok(expenseResponse);
    }


    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long expenseId) {
        expenseService.deleteExpense(expenseId);
        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getListExpense() {
        List<Expense> expenses = expenseService.getListExpense();
        List<ExpenseResponse> expenseResponses = expenses.stream()
                .map(this::mapToExpenseResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(expenseResponses);
    }

    private ExpenseResponse mapToExpenseResponse(Expense expense) {
        ExpenseResponse expenseResponse = new ExpenseResponse();
        expenseResponse.setExpenseCategoryName(expense.getExpenseCategory().getExpenseCategoryName());
        expenseResponse.setDate(expense.getDate());
        expenseResponse.setAmount(expense.getAmount());
        expenseResponse.setDescription(expense.getDescription());
        expenseResponse.setAssetName(expense.getAsset().getAssetName());
        return expenseResponse;
    }


    @GetMapping("/total-by-day")
    public ResponseEntity<BigDecimal> getTotalAmountByDay(@RequestParam("date") Date date, Principal principal) {
        String username = principal.getName();
        User loggedInUser = userService.findUserByEmail(username);
        BigDecimal totalAmount = expenseService.getTotalAmountByDay(date, loggedInUser);
        return ResponseEntity.ok(totalAmount);
    }

    @GetMapping("/total-by-week")
    public ResponseEntity<BigDecimal> getTotalAmountByWeek(@RequestParam("date") Date date, Principal principal) {
        String username = principal.getName();
        User loggedInUser = userService.findUserByEmail(username);
        BigDecimal totalAmount = expenseService.getTotalAmountByWeek(date, loggedInUser);
        return ResponseEntity.ok(totalAmount);
    }

    @GetMapping("/total-by-month")
    public ResponseEntity<BigDecimal> getTotalAmountByMonth(@RequestParam("date") Date date, Principal principal) {
        String username = principal.getName();
        User loggedInUser = userService.findUserByEmail(username);
        BigDecimal totalAmount = expenseService.getTotalAmountByMonth(date, loggedInUser);
        return ResponseEntity.ok(totalAmount);
    }

    @GetMapping("/total-by-year")
    public ResponseEntity<BigDecimal> getTotalAmountByYear(@RequestParam("year") int year, Principal principal) {
        String username = principal.getName();
        User loggedInUser = userService.findUserByEmail(username);
        BigDecimal totalAmount = expenseService.getTotalAmountByYear(year, loggedInUser);
        return ResponseEntity.ok(totalAmount);
    }
}
