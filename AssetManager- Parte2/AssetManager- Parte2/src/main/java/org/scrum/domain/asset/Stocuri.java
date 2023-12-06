package org.scrum.domain.asset;

import jakarta.persistence.Entity;

@Entity
public class Stocuri extends Asset{

    public Stocuri() {
        super();
        super.setAssetType(AssetType.STOCURI);
    }

    public void setAssetType(AssetType assetType) {
        throw new Error("Proprietatea tip nu poate fi schimbata!");
    }
}
