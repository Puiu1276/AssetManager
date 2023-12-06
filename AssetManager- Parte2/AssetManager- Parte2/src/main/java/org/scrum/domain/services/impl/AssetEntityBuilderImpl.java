package org.scrum.domain.services.impl;

import org.scrum.domain.asset.Asset;
import org.scrum.domain.services.IAssetEntityBuilder;
import org.scrum.domain.services.IAssetEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AssetEntityBuilderImpl implements IAssetEntityBuilder {
    private String assetName;
    private String destinatieLocatie;


    @Autowired
    private IAssetEntityFactory entityFactory;

    private AssetEntityBuilderImpl(IAssetEntityFactory entityFactory){this.entityFactory = entityFactory;}

    @Override
    public IAssetEntityBuilder startBuilding() {
        return new AssetEntityBuilderImpl(this.entityFactory);
    }

    @Override
    public IAssetEntityBuilder assetName(String assetName) {
        this.assetName = assetName;
        return this;
    }

    @Override
    public IAssetEntityBuilder destinatieLocatie(String destinatieLocatie) {
        this.destinatieLocatie = destinatieLocatie;
        return this;
    }

    @Override
    public Asset build() {
        if(this.assetName != null && this.destinatieLocatie != null)
            return this.entityFactory.buildAssetWithDestinationLocation(assetName, destinatieLocatie);
        throw new RuntimeException("Not enough data to build Asset!");
    }
}
