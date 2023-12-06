package org.scrum.domain.services.impl;

import jakarta.annotation.PostConstruct;
import org.scrum.domain.angajati.Responsabil;
import org.scrum.domain.asset.Asset;
import org.scrum.domain.location.Location;
import org.scrum.domain.operatiune.Opeariune;
import org.scrum.domain.services.IAssetEntityFactory;
import org.scrum.domain.services.IAssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
@Scope("singleton")
public class AssetEntityFactoryJPA implements IAssetEntityFactory {
    private static Logger logger = Logger.getLogger(AssetEntityFactoryJPA.class.getName());
    public AssetEntityFactoryJPA() {
        logger.info(">>> BEAN: AssetEntityFactoryCDI instantiated!");
    }

    // build project with: 1 release, startDate is Now, release publish date:
    // startDate + 1 Month
    @Override
    public Asset buildSimpleAsset(String assetName) {
        Asset newAsset = new Asset();
        newAsset.setAssetName(assetName);

        return newAsset;
    }


    @Override
    public Asset buildAssetWithDestinationLocation(String assetName, String destinatieLocatie) {
        Asset newAsset = new Asset();
        newAsset.setAssetName(assetName);

        // Setarea locației curente folosind detalii din parametrul 'locatieCurenta'
        // Presupunând că 'locatieCurenta' este numele locației
        Location currentLocation = new Location();
        currentLocation.setLocationName(destinatieLocatie);

        currentLocation.setCountry("Romania"); currentLocation.setCity("Iasi"); currentLocation.setAdress("Mihai Viteazu nr. 8");

        currentLocation.setAsset(newAsset);
        newAsset.setCurrentLocation(currentLocation);

        return newAsset;
    }

    @Override
    public Asset buildAssetWithOperatiune(String assetName, String locatieCurentaName, String destinatieLocatieName, String descriereOperatiune) {
        Asset newAsset = new Asset();
        newAsset.setAssetName(assetName);

        Location currentLocation = new Location();
        currentLocation.setLocationName(locatieCurentaName);
        currentLocation.setCountry("Romania");
        currentLocation.setCity("Iasi");
        currentLocation.setAdress("Mihai Viteazu nr. 8");
        currentLocation.setAsset(newAsset);
        newAsset.setCurrentLocation(currentLocation);

        Location destinatieLocatie = new Location();
        destinatieLocatie.setLocationName(destinatieLocatieName);
        destinatieLocatie.setCountry("Romania");
        destinatieLocatie.setCity("Bucuresti");
        destinatieLocatie.setAdress("Splaiul Independentei nr 10");

        Opeariune operatiune = new Opeariune();
        operatiune.setDescriereOperatiune(descriereOperatiune);
        operatiune.setDescriereOperatiune("Operatiune de transfer.");

        newAsset.setOpeariune(operatiune);

        return newAsset;
    }


    @Override
    public Asset buildAssetWithResponsabil(String assetName, List<String> memberNames) {
        Asset newAsset = new Asset();
        newAsset.setAssetName(assetName);

        List<Responsabil> responsabil = new ArrayList<>();
        for (String name : memberNames) {
            Responsabil member = new Responsabil();
            member.setMemberName(name);
            responsabil.add(member);
        }
        newAsset.setResponsabil(responsabil);

        return newAsset;
    }


    // Dependency
    @Autowired
    private IAssetRepository entityRepository;

    //@Override
    public void setAssetEntityRepository(IAssetRepository repository) {
        this.entityRepository = repository;
    }

    public AssetEntityFactoryJPA(IAssetRepository entityRepository) {
        super();
        this.entityRepository = entityRepository;
    }

    // build Project entity from DTO and update with DTO
    @Override
    public Asset toEntity(Asset assetDTO) {
        Asset assetEntity = this.entityRepository.findById(assetDTO.getAssetID()).orElseThrow();
        assetEntity.setAssetName(assetDTO.getAssetName());
        assetEntity.setAssetType(assetDTO.getAssetType());
        return assetEntity;
    }

    @PostConstruct
    @Override
    public void initDomainServiceEntities() {
        logger.info(">> PostConstruct :: initDomainServiceEntities");
        for (int i = 1; i <= 3; i++) {
            String assetName = "Asset_" + i;
            String locatieCurentaName = "LocatieCurenta_" + i;
            String destinatieLocatieName = "DestinatieLocatie_" + i;
            String descriereOperatiune = "DescriereOperatiune_" + i;

            Asset newAsset = buildAssetWithOperatiune(assetName, locatieCurentaName, destinatieLocatieName, descriereOperatiune);

            entityRepository.save(newAsset);
        }
        logger.info(">> AssetEntityRepository asset.count :: " + entityRepository.count());
    }

    @Override
    public void save(Asset asset) {

    }


}
