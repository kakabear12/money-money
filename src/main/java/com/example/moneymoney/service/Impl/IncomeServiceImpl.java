package com.example.moneymoney.service.Impl;

import com.example.moneymoney.entity.Asset;
import com.example.moneymoney.entity.Income;
import com.example.moneymoney.entity.IncomeCategory;
import com.example.moneymoney.entity.User;
import com.example.moneymoney.model.requestmodel.IncomeRequestModel;
import com.example.moneymoney.model.responsemodel.IncomeResponse;
import com.example.moneymoney.repository.AssetRepository;
import com.example.moneymoney.repository.IncomeCategoryRepository;
import com.example.moneymoney.repository.IncomeRepository;
import com.example.moneymoney.service.IncomeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository incomeRepository;

    private final IncomeCategoryRepository incomeCategoryRepository;
    private final AssetRepository assetRepository;

    private final ModelMapper modelMapper;



    @Override
    public IncomeResponse createIncome(IncomeRequestModel incomeRequest, User loggedInUser) {
        String incomeCategoryName = incomeRequest.getIncomeCategoryName();
        String assetName = incomeRequest.getAssetName();

        IncomeCategory incomeCategory = incomeCategoryRepository.findByIncomeCategoryName(incomeCategoryName);
        if (incomeCategory == null) {
            throw new IllegalArgumentException("Income category not found");
        }

        Asset asset = assetRepository.findByAssetName(assetName);
        if (asset == null) {
            throw new IllegalArgumentException("Asset not found");
        }

        Income income = new Income();
        income.setUser(loggedInUser);
        income.setIncomeCategory(incomeCategory);
        income.setDate(incomeRequest.getDate());
        income.setAmount(incomeRequest.getAmount());
        income.setDescription(incomeRequest.getDescription());
        income.setAsset(asset);

        Income createdIncome = incomeRepository.save(income);

        IncomeResponse incomeResponse = new IncomeResponse();
        incomeResponse.setIncomeCategoryName(createdIncome.getIncomeCategory().getIncomeCategoryName());
        incomeResponse.setDate(createdIncome.getDate());
        incomeResponse.setAmount(createdIncome.getAmount());
        incomeResponse.setDescription(createdIncome.getDescription());
        incomeResponse.setAssetName(createdIncome.getAsset().getAssetName());

        return incomeResponse;
    }

    @Override
    public IncomeResponse getIncomeById(Long incomeId) {
        Income income = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new RuntimeException("Income not found"));

        IncomeResponse incomeResponse = new IncomeResponse();
        incomeResponse.setIncomeCategoryName(income.getIncomeCategory().getIncomeCategoryName());
        incomeResponse.setDate(income.getDate());
        incomeResponse.setAmount(income.getAmount());
        incomeResponse.setDescription(income.getDescription());
        incomeResponse.setAssetName(income.getAsset().getAssetName());
        return incomeResponse;
    }

    @Override
    public IncomeResponse updateIncome(Long incomeId, IncomeRequestModel incomeRequest) {
        Income existingIncome = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new RuntimeException("Income not found"));

        IncomeCategory incomeCategory = incomeCategoryRepository.findByIncomeCategoryName(incomeRequest.getIncomeCategoryName());
        if (incomeCategory == null) {
            throw new IllegalArgumentException("Income category not found");
        }

        existingIncome.setIncomeCategory(incomeCategory);
        existingIncome.setDate(incomeRequest.getDate());
        existingIncome.setAmount(incomeRequest.getAmount());
        existingIncome.setDescription(incomeRequest.getDescription());

        Asset asset = existingIncome.getAsset();
        if (asset == null) {
            asset = new Asset();
            existingIncome.setAsset(asset);
        }
        asset.setAssetName(incomeRequest.getAssetName());

        Income updatedIncome = incomeRepository.save(existingIncome);

        IncomeResponse incomeResponse = new IncomeResponse();
        incomeResponse.setIncomeCategoryName(updatedIncome.getIncomeCategory().getIncomeCategoryName());
        incomeResponse.setDate(updatedIncome.getDate());
        incomeResponse.setAmount(updatedIncome.getAmount());
        incomeResponse.setDescription(updatedIncome.getDescription());
        incomeResponse.setAssetName(updatedIncome.getAsset().getAssetName());

        return incomeResponse;
    }

    @Override
    public void deleteIncome(Long incomeId) {
        Income income = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new RuntimeException("Income not found"));

        incomeRepository.delete(income);
    }

    @Override
    public List<Income> getListIncome() {
        return incomeRepository.findAll();
    }

    @Override
    public BigDecimal getTotalAmountByDay(Date date, User loggedInUser) {
        return incomeRepository.getTotalAmountByDay(date,loggedInUser);
    }

    @Override
    public BigDecimal getTotalAmountByWeek(Date date, User loggedInUser) {
        return incomeRepository.getTotalAmountByWeek(date,loggedInUser);
    }

    @Override
    public BigDecimal getTotalAmountByMonth(Date date, User loggedInUser) {
        return incomeRepository.getTotalAmountByMonth(date,loggedInUser);
    }

    @Override
    public BigDecimal getTotalAmountByYear(int year, User loggedInUser) {
        return incomeRepository.getTotalAmountByYear(year,loggedInUser);
    }
}