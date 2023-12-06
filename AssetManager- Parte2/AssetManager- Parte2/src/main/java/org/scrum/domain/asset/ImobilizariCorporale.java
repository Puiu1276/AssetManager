package org.scrum.domain.asset;

import jakarta.persistence.Entity;

@Entity
public class ImobilizariCorporale extends Asset{

    public ImobilizariCorporale() {
        super();
        super.setAssetType(AssetType.IMOBILIZARICORPORALE);
    }

    public void setAssetType(AssetType assetType) {
        throw new Error("Proprietatea tip nu poate fi schimbata!");
    }

}
