package org.scrum.domain.services;

import org.scrum.domain.asset.Asset;
import org.scrum.domain.asset.AssetCategory;

import java.util.List;

public interface IAssetEntityFactory {

    // Method to build a simple asset with default configuration
    public Asset createAsset(String name, String location, AssetCategory category);
    // Dependency injection methods
    public void setAssetRepository(IAssetEntityRepository repository);
    public IAssetEntityRepository getAssetEntityRepository();
    public IAssetEntityRepository getAssetRepository();
    public Integer getAssetCount();
    public List<Asset> getAllAssets();
    public Asset getAssetById(Integer assetId);
    public List<Asset> getAssetsByCategory(String category);
    public Asset getAssetByName(String name);

}
