package org.scrum.domain.services;

import org.scrum.domain.asset.Asset;
import org.scrum.domain.asset.AssetDestinatieLocatieView;

import java.util.Collection;
import java.util.List;

public interface IAssetCurrentLocationConsolidatingService {

    List<AssetDestinatieLocatieView> getAssetDestinatieLocatieViewDataOf(Collection<Asset> assets);

    AssetDestinatieLocatieView getAssetDestinatieLocatieViewDataOf(Asset asset);
}
