package org.scrum.domain.services;

import org.scrum.domain.asset.Asset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.logging.Logger;

//SDI Bean Component
@Component
@Scope("singleton")
public  class AssetEntityRepositoryImpl implements IAssetEntityRepository {
    private static Logger logger = Logger.getLogger(AssetEntityRepositoryImpl.class.getName());

    public AssetEntityRepositoryImpl() {
        logger.info(">>> BEAN: AssetEntityRepositoryImpl instantiated!");
    }

    private Map<Integer, Asset> assets = new HashMap<>();

    private Integer nextID = 0;

    // Increment and get the next asset ID
    @Override
    public Integer getNextID() {
        nextID++;
        return nextID;
    }

    // Retrieve an asset by its ID
    @Override
    public Asset getById(Integer id) {
        return assets.get(id);
    }

    @Override
    public Asset get(Asset sample) {
        return getById(sample.getAssetID());
    }

    // Retrieve all assets as a collection
    @Override
    public Collection<Asset> toCollection() {
        return new ArrayList<>(assets.values());
    }

    // Add a new asset to the repository
    @Override
    public Asset add(Asset entity) {
        if (entity.getAssetID() == null) {
            entity.setAssetID(getNextID());
        }
        assets.put(entity.getAssetID(), entity);
        return entity;
    }

    // Add multiple assets to the repository
    @Override
    public Collection<Asset> addAll(Collection<Asset> entities) {
        for (Asset entity : entities) {
            this.add(entity);
        }
        return entities;
    }

    // Remove an asset from the repository
    @Override
    public boolean remove(Asset entity) {
        if (assets.containsKey(entity.getAssetID())) {
            assets.remove(entity.getAssetID());
            return true;
        }
        return false;
    }

    // Remove multiple assets from the repository
    @Override
    public boolean removeAll(Collection<Asset> entities) {
        boolean allRemoved = true;
        for (Asset entity : entities) {
            if (!remove(entity)) {
                allRemoved = false;
            }
        }
        return allRemoved;
    }

    // Get the number of assets in the repository
    @Override
    public int size() {
        return assets.size();
    }
}
