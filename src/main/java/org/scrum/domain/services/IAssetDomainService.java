package org.scrum.domain.services;

import org.scrum.domain.asset.Asset;
import org.scrum.domain.asset.AssetCategory;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IAssetDomainService {
    IAssetEntityRepository getAssetRepository();

    // Obtine o lista cu toate activele
    public List<Asset> getAllAssets();

    // Obtine numarul total de active
    public Integer getTotalAssetCount();

    // Obtine activele de o anumita categorie
    public List<Asset> getAssetsByCategory(AssetCategory category);

    // Obtine numarul de active dintr-o anumita categorie
    public Integer getAssetCountByCategory(AssetCategory category);

    Integer getAssetCount();

    // Obtine un activ dupa ID
    public Asset getAssetById(Integer assetID);

    List<Asset> getAssetsByCategory(String category);

    // Obtine un activ dupa nume
    public Asset getAssetByName(String name);

    // Actualizeaza locatia unui activ
    public void updateAssetLocation(Integer assetID, String newLocation);

    // Adauga un nou activ
    public void addNewAsset(Asset asset);

    // Sterge un activ
    public void deleteAsset(Integer assetID);

    Asset buildSimpleAsset(String assetName);

    Asset buildAssetWithCustomDepreciation(String assetName, Date acquisitionDate, Integer depreciationPeriodInMonths);

    Asset buildAssetWithCustomDepreciation(String assetName, LocalDateTime acquisitionDate, Integer depreciationPeriodInMonths);

    Asset buildAssetWithMaintenanceSchedules(String assetName, List<Date> maintenanceStartDates);

    // Setează dependințele necesare pentru a interacționa cu repository-ul de active
    public void setAssetRepository(IAssetEntityRepository repository);
}
