package com.example.moneymoney.repository;

import com.example.moneymoney.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    Asset findByAssetName(String assetName);
}