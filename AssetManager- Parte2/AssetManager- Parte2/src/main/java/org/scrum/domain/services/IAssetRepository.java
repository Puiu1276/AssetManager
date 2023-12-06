package org.scrum.domain.services;

import org.scrum.domain.asset.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAssetRepository extends JpaRepository<Asset, Integer>{
//                  	extends CrudRepository<Project, Integer>{
        // Queriable Named Operations
    @Query("SELECT a FROM Asset a WHERE a.name like %:name%")
    List<Asset> findByName(String name);

    @Query("SELECT a FROM Asset a WHERE a.assetID = :assetID")
    Asset findByAssetID(Integer assetID);

    @Query("SELECT a FROM Asset a WHERE a.name like %:aname%")
    List<Asset> findByAssetName(@Param("aname") String name);

    @Query("SELECT a FROM Asset a WHERE a.name like %:aname% OR a.assetID = :aID")
    List<Asset> findByAssetNameOrAssetID(
            @Param("aname") String name,
            @Param("aID") String aID);
}
