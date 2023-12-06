package org.scrum.domain.services;

import org.scrum.domain.asset.Asset;

import java.util.List;

public interface IAssetEntityFactory {
    public Asset buildSimpleAsset(String assetName);
    //
    public Asset buildAssetWithDestinationLocation(String assetName, String destinatieLocatie);
    //
    public Asset buildAssetWithOperatiune(String assetName, String locatieCurenta, String destinatieLocatie, String descriereOperatiune);
    //
    public Asset buildAssetWithResponsabil(String assetName, List<String> memberNames);
    // Dependencies to get IDs
    public void setAssetEntityRepository(IAssetRepository repository);
    // build asset from DTO
    public Asset toEntity(Asset assetDTO);

    public void initDomainServiceEntities() ;

    void save(Asset asset);
}
