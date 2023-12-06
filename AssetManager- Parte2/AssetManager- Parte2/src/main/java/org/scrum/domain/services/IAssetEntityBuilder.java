package org.scrum.domain.services;

import org.scrum.domain.asset.Asset;

import java.util.Date;

public interface IAssetEntityBuilder {
    //
    IAssetEntityBuilder startBuilding();
    //
    IAssetEntityBuilder assetName(String assetName);
    IAssetEntityBuilder destinatieLocatie(String destinatieLocatie);

    Asset build();
    //
}
