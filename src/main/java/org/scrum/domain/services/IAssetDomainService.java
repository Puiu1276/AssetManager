package org.scrum.domain.services;

import org.scrum.domain.asset.Asset;
import org.scrum.domain.asset.AssetCategory;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface IAssetDomainService {

    // Obtine o lista cu toate activele
    public List<Asset> getAllAssets();


    // Obtine activele de o anumita categorie
    public List<Asset> getAssetsByCategory(AssetCategory category);

    // Obtine numarul de active dintr-o anumita categorie
    public Integer getAssetCountByCategory(AssetCategory category);

    public Integer getAssetCount();

    public Asset getAssetById(Integer assetId);

    public Asset getAssetByName(String name);
    // Obtine numarul total de active
    public Integer getAssetCount(Asset assetID);

    Integer getAssetCount(Integer assetID);

    public Integer getAssetsCount(Asset asset);

    public Integer getAssetsCount(Integer assetID);

    // Actualizeaza locatia unui activ
    public void updateAssetLocation(Integer assetID, String newLocation);


    // Setează dependințele necesare pentru a interacționa cu repository-ul de active
    public void setAssetRepository(IAssetEntityRepository repository);
}
