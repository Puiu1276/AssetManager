package org.scrum.domain.services;

import org.scrum.domain.asset.Asset;
import org.scrum.domain.asset.AssetCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public  class AssetDomainServiceImpl implements IAssetDomainService {

    @Autowired
    private IAssetEntityRepository assetRepository;

    @Autowired
    public AssetDomainServiceImpl(IAssetEntityRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public List<Asset> getAllAssets() {
        return new ArrayList<>(assetRepository.toCollection());
    }

    @Override
    public Integer getAssetCount() {
        return assetRepository.size();
    }

    @Override
    public Asset getAssetById(Integer assetId) {
        return assetRepository.getById(assetId);
    }

    @Override
    public List<Asset> getAssetsByCategory(AssetCategory category) {
        return assetRepository.toCollection().stream()
                .filter(asset -> asset.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public void setAssetRepository(IAssetEntityRepository repository) {
        this.assetRepository = repository;
    }

    @Override
    public Asset getAssetByName(String name) {
        return assetRepository.toCollection().stream()
                .filter(asset -> asset.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Integer getAssetCountByCategory(AssetCategory category) {
        // Folosim stream-ul pentru a filtra activele după categorie și a număra rezultatele.
        return (int) assetRepository.toCollection().stream()
                .filter(asset -> asset.getCategory().equals(category))
                .count();
    }
    @Override
    public void updateAssetLocation(Integer assetID, String newLocation) {

    }

}

