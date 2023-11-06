package org.scrum.domain.services;

import jakarta.annotation.PostConstruct;
import org.scrum.domain.asset.Asset;
import org.scrum.domain.asset.AssetCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component("AssetEntityFactoryBaseCDI")
@Scope("singleton")
public class AssetEntityFactoryImpl implements IAssetEntityFactory{
    private static Logger logger = Logger.getLogger(AssetEntityFactoryImpl.class.getName());

    @Autowired
    private IAssetEntityRepository assetRepository;
    @Autowired
    public AssetEntityFactoryImpl(IAssetEntityRepository assetEntityRepository) {
        this.assetRepository = assetEntityRepository;
        logger.info(">>> BEAN: AssetEntityFactoryImpl instantiated!");
    }

    @Override
    public Asset createAsset(String name, String location, AssetCategory category) {
        Integer nextID = assetRepository.getNextID();
        Asset newAsset = new Asset(nextID, name, location, category);
        assetRepository.add(newAsset);
        return newAsset;
    }

    // Aici puteți adăuga metode suplimentare pentru crearea de active cu configurații specifice, dacă este necesar

    @Override
    public void setAssetRepository(IAssetEntityRepository repository) {
        this.assetRepository = repository;
    }

    @Override
    public IAssetEntityRepository getAssetEntityRepository() {
        return this.assetRepository;
    }

    @Override
    public IAssetEntityRepository getAssetRepository() {
        return this.assetRepository;
    }

    @Override
    public List<Asset> getAllAssets() {
        return (List<Asset>) assetRepository.toCollection();
    }

    @Override
    public Asset getAssetById(Integer assetId) {
        return assetRepository.getById(assetId);
    }

    @Override
    public List<Asset> getAssetsByCategory(String category) {
        return assetRepository.getAssetsByCategory(AssetCategory.valueOf(category.toUpperCase()));
    }

    @Override
    public Asset getAssetByName(String name) {
        return assetRepository.getAssetByName(name);
    }

    @Override
    public Integer getAssetCount() {
        return assetRepository.size();
    }

    // Metoda PostConstruct pentru a initializa fabrica sau pentru a pre-popula repository-ul cu active
    @PostConstruct
    private void initAssets() {
        logger.info(">> PostConstruct :: initAssets");
        assetRepository.add(createAsset("Laptop", "Main Building, 304 Cuza Voda Street", AssetCategory.IMOBILIZARI_CORPORALE));
        assetRepository.add(createAsset("Office Desk", "Main Building, 304 Cuza Voda Street", AssetCategory.IMOBILIZARI_CORPORALE));
        assetRepository.add(createAsset("Warehouse", "129 Unirii Street", AssetCategory.IMOBILIZARI_CORPORALE));
        assetRepository.add(createAsset("Raw Materials", "Warehouse Shelf 1", AssetCategory.STOCURI));
        assetRepository.add(createAsset("Finished Goods", "Warehouse Shelf 2", AssetCategory.STOCURI));
        logger.info(">> AssetRepository asset.count :: " + assetRepository.size());
    }
}
