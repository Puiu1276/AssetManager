package org.scrum.domain.services;

import org.scrum.domain.asset.Asset;
import org.scrum.domain.asset.AssetCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssetDomainServiceImpl implements IAssetDomainService {

    private IAssetEntityRepository assetRepository;

    @Override
    public Asset buildSimpleAsset(String assetName) {
        return null;
    }

    @Override
    public Asset buildAssetWithCustomDepreciation(String assetName, Date acquisitionDate, Integer depreciationPeriodInMonths) {
        return null;
    }

    @Override
    public Asset buildAssetWithCustomDepreciation(String assetName, LocalDateTime acquisitionDate, Integer depreciationPeriodInMonths) {
        return null;
    }

    @Override
    public Asset buildAssetWithMaintenanceSchedules(String assetName, List<Date> maintenanceStartDates) {
        return null;
    }

    @Autowired
    public void setAssetRepository(IAssetEntityRepository repository) {
        this.assetRepository = repository;
    }

    @Override
    public IAssetEntityRepository getAssetRepository() {
        return null;
    }

    public AssetDomainServiceImpl() {
        super();
    }

    @Autowired
    public AssetDomainServiceImpl(IAssetEntityRepository assetRepository) {
        super();
        this.assetRepository = assetRepository;
    }

    @Override
    public List<Asset> getAllAssets() {
        return assetRepository.toCollection().stream().collect(Collectors.toList());
    }

    @Override
    public Integer getTotalAssetCount() {
        return null;
    }

    @Override
    public List<Asset> getAssetsByCategory(AssetCategory category) {
        return null;
    }

    @Override
    public Integer getAssetCountByCategory(AssetCategory category) {
        return null;
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
    public List<Asset> getAssetsByCategory(String category) {
        return assetRepository.toCollection().stream()
                .filter(asset -> category.equals(asset.getCategory()))
                .collect(Collectors.toList());
    }



    @Override
    public Asset getAssetByName(String assetName) {
        List<Asset> assets = getAllAssets();
        return assets.stream()
                .filter(asset -> assetName.equals(asset.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateAssetLocation(Integer assetID, String newLocation) {

    }

    @Override
    public void addNewAsset(Asset asset) {

    }

    @Override
    public void deleteAsset(Integer assetID) {

    }
}

