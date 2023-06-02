package com.example.moneymoney.service.Impl;

import com.example.moneymoney.entity.Asset;
import com.example.moneymoney.repository.AssetRepository;
import com.example.moneymoney.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {
    private  final AssetRepository assetRepository;
    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }
}
