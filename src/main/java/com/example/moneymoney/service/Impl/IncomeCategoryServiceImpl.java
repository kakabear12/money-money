package com.example.moneymoney.service.Impl;

import com.example.moneymoney.entity.IncomeCategory;
import com.example.moneymoney.repository.IncomeCategoryRepository;
import com.example.moneymoney.service.IncomeCategoryService;
import com.example.moneymoney.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IncomeCategoryServiceImpl implements IncomeCategoryService {
    private final IncomeCategoryRepository incomeCategoryRepository;
    @Override
    public List<IncomeCategory> getAllIncomeCategories() {
        return incomeCategoryRepository.findAll();
    }
}
