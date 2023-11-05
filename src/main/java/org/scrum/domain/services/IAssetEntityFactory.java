package org.scrum.domain.services;
import org.scrum.domain.asset.Asset;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IAssetEntityFactory {

    // Method to build a simple asset with default configuration
    Asset buildSimpleAsset(String assetName);

    // Method to build an asset with custom acquisition date and depreciation period
    Asset buildAssetWithCustomDepreciation(String assetName, Date acquisitionDate, Integer depreciationPeriodInMonths);

    // Method to build an asset with custom acquisition date and depreciation period using LocalDateTime
    Asset buildAssetWithCustomDepreciation(String assetName, LocalDateTime acquisitionDate, Integer depreciationPeriodInMonths);

    // Method to build an asset and track multiple maintenance schedules
    Asset buildAssetWithMaintenanceSchedules(String assetName, List<Date> maintenanceStartDates);

    // Dependency injection methods
    void setAssetRepository(IAssetEntityRepository repository);
    IAssetEntityRepository getAssetRepository();

    List<Asset> getAllAssets();

    Integer getAssetCount();

    Asset getAssetById(Integer assetId);

    List<Asset> getAssetsByCategory(String category);

    Integer getAssetsCountByCategory(String type);

    Asset getAssetByName(String assetName);
}
