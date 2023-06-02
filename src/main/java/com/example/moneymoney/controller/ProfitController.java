package com.example.moneymoney.controller;

import com.example.moneymoney.entity.User;
import com.example.moneymoney.service.ProfitService;
import com.example.moneymoney.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

@RestController
@RequestMapping("api/v1/money-money/users/profits")
@Slf4j
@RequiredArgsConstructor
public class ProfitController {
    private final ProfitService profitService;

    private final UserService userService;

    @GetMapping("/profit-by-day")
    public ResponseEntity<BigDecimal> getProfitByDay(@RequestParam("date") Date date, Principal principal) {
        String username = principal.getName();
        User loggedInUser = userService.findUserByEmail(username);
        BigDecimal profit = profitService.getProfitByDay(date, loggedInUser);
        return ResponseEntity.ok(profit);
    }

    @GetMapping("/profit-by-week")
    public ResponseEntity<BigDecimal> getProfitByWeek(@RequestParam("date") Date date, Principal principal) {
        String username = principal.getName();
        User loggedInUser = userService.findUserByEmail(username);
        BigDecimal profit = profitService.getProfitByWeek(date, loggedInUser);
        return ResponseEntity.ok(profit);
    }

    @GetMapping("/profit-by-month")
    public ResponseEntity<BigDecimal> getProfitByMonth(@RequestParam("date") Date date, Principal principal) {
        String username = principal.getName();
        User loggedInUser = userService.findUserByEmail(username);
        BigDecimal profit = profitService.getProfitByMonth(date, loggedInUser);
        return ResponseEntity.ok(profit);
    }

    @GetMapping("/profit-by-year")
    public ResponseEntity<BigDecimal> getProfitByYear(@RequestParam("year") int year, Principal principal) {
        String username = principal.getName();
        User loggedInUser = userService.findUserByEmail(username);
        BigDecimal profit = profitService.getProfitByYear(year, loggedInUser);
        return ResponseEntity.ok(profit);
    }

}
