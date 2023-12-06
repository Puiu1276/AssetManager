package org.scrum.domain.services.impl;

import org.scrum.domain.angajati.Responsabil;
import org.scrum.domain.asset.Asset;
import org.scrum.domain.asset.AssetDestinatieLocatieView;
import org.scrum.domain.location.Location;
import org.scrum.domain.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class PlanningAssetWorkflowServiceImpl implements IPlanningAssetWorkflowService {
    private static Logger logger = Logger.getLogger(PlanningAssetWorkflowServiceImpl.class.getName());

    // Support Services
    @Autowired
    private IAssetRepository entityRepository;

    @Autowired
    private IAssetEntityFactory entityFactory;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private IAssetCurrentLocationConsolidatingService consolidatingAssetService;


    @Override
    public Integer planNewAsset(String assetName) {
        Asset asset = entityFactory.buildSimpleAsset(assetName);

        entityFactory.save(asset);
        return asset.getAssetID();
    }

    @Override
    public Integer addResponsabilToAsset(Integer assetID, Integer memberID, String memberName) {
        Asset asset = entityRepository.getById(assetID);
        List<Responsabil> responsabil = asset.getResponsabil();
        asset.addResponsabil(memberName);

        entityFactory.save(asset);
        return asset.getAssetID();
    }


    @Override
    public AssetDestinatieLocatieView getAssetSummaryData(Integer assetID) {
        Asset asset = entityRepository.getById(assetID);
        applicationEventPublisher.publishEvent(new DomainEvent(this, asset));

        return consolidatingAssetService.getAssetDestinatieLocatieViewDataOf(asset);
    }


    @Override
    public Integer planDestinationLocation(Integer assetID, String destinatieLocatie) {
        Asset asset = entityRepository.getById(assetID);
        Location locatieDestinatie = asset.getDestinatieLocatie();
        locatieDestinatie.setDestinatieLocatie(locatieDestinatie);

        entityFactory.save(asset);
        return asset.getDestinatieLocatie().getLocationID();
    }

}
