package org.scrum.domain.services;

import jakarta.annotation.PostConstruct;
import org.scrum.domain.asset.Asset;
import org.scrum.domain.asset.AssetCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component("AssetFactoryBaseCDI")
@Scope("singleton")
public class AssetEntityFactoryImpl implements IAssetEntityFactory{
    private static Logger logger = Logger.getLogger(AssetEntityFactoryImpl.class.getName());

    @Autowired
    private IAssetEntityRepository assetRepository;

    public AssetEntityFactoryImpl() {
        logger.info(">>> BEAN: AssetFactoryImpl instantiated!");
    }

    public Asset createAsset(String name, String description, AssetCategory category) {
        Integer nextID = assetRepository.getNextID();
        Asset newAsset = new Asset(nextID, name, description, category);
        // Aici poti adauga logica pentru setarea altor proprietati specifice unui activ nou creat
        return newAsset;
    }

    // Alte metode pentru a construi diferite tipuri de active sau configuratii

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

    public void setAssetRepository(IAssetEntityRepository repository) {
        this.assetRepository = repository;
    }

    public IAssetEntityRepository getAssetRepository() {
        return assetRepository;
    }

    @Override
    public List<Asset> getAllAssets() {
        return null;
    }

    @Override
    public Integer getAssetCount() {
        return null;
    }

    @Override
    public Asset getAssetById(Integer assetId) {
        return null;
    }

    @Override
    public List<Asset> getAssetsByCategory(String category) {
        return null;
    }

    @Override
    public Integer getAssetsCountByCategory(String type) {
        return null;
    }

    @Override
    public Asset getAssetByName(String assetName) {
        return null;
    }

    // Metoda PostConstruct pentru a initializa fabrica sau pentru a pre-popula repository-ul cu active, dacă e necesar
    @PostConstruct
    private void initAssets() {
        logger.info(">> PostConstruct :: initAssets");
        assetRepository.add(createAsset("Laptop", "High-end business laptop", AssetCategory.IMOBILIZARI_CORPORALE));
        assetRepository.add(createAsset("Office Desk", "Ergonomic office desk", AssetCategory.IMOBILIZARI_CORPORALE));
        // și așa mai departe...
        logger.info(">> AssetRepository asset count :: " + assetRepository.size());
    }
}
