package org.scrum.domain.services.impl;

import org.scrum.domain.asset.Asset;
import org.scrum.domain.asset.AssetDestinatieLocatieView;
import org.scrum.domain.services.IAssetCurrentLocationConsolidatingService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetDestinatieLocatieConsolidatingServiceImpl implements IAssetCurrentLocationConsolidatingService {

    @Override
    public AssetDestinatieLocatieView getAssetDestinatieLocatieViewDataOf(Asset asset) {
        return new AssetDestinatieLocatieView(
                asset.getAssetID(), asset.getAssetName(),
                asset.getDestinatieLocatie().getLocationName());
    }
    @Override
    public List<AssetDestinatieLocatieView> getAssetDestinatieLocatieViewDataOf(Collection<Asset> assets) {
        List<AssetDestinatieLocatieView> viewDataList =
                assets.stream()
                        .map(asset -> getAssetDestinatieLocatieViewDataOf(asset))
                        .collect(Collectors.toList());

        return viewDataList;
    }
}


