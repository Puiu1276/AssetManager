package org.scrum.domain.services;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.scrum.domain.asset.Asset;
import org.scrum.domain.asset.Asset.AssetType;
import org.scrum.domain.asset.AssetGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

//JUnit.5
@SpringBootTest
public class TestAssetEntityRepositorySpringData {
    private static Logger logger = Logger.getLogger(TestAssetEntityRepositorySpringData.class.getName());

    @Autowired
    private IAssetEntityFactory entityFactory;

    // Strategy_3: Spring Data JPA declared Repository
    @Autowired //@Qualifier("IProjectRepositorySpringData")
    private IAssetRepository entityRepository;

    @Test
    @Order(1)
    public void test() throws Exception{
        // Check CDI Beans (business services)
        logger.info("Repository implementation object:: " + entityRepository);
        logger.info("Repository implementation class:: " + entityRepository.getClass().getName());
        //
        logger.info("Factory implementation object:: " + entityFactory);
        logger.info("Factory implementation class:: " + entityFactory.getClass().getName());
        //
        tearDown();

        // Create and persist entities
        List<Asset> testAssets = new ArrayList<>();
        Asset testAsset;
        for(int i=1; i<=3; i++) {
            testAsset = entityFactory.buildAssetWithOperatiune("Asset_" + i,  "Iasi", "Bucuresti", "Operatiune de transfer.");

            // testing converter
            testAsset.setAssetGroup(new AssetGroup("Vehicule"));
            testAsset.getDestinatieLocatie().addDestinatieLocatie("Garaj_A");

            testAsset = entityRepository.save(testAsset);
            testAsset.add(testAsset);
            logger.info("Saved project: " + testAsset);
        }

        // Check entities
        Long assetCount = entityRepository.count();
        assertTrue(assetCount > 0, "AssetCount not counting...");
        logger.info("projectCount = " + assetCount);
        testAsset= testAssets.get(0);
        Integer responsabiliCount = testAsset.getResponsabil().size();
        assertTrue(responsabiliCount > 0, "AsssetCount not counting...");
        logger.info("responsabiliCount = " + responsabiliCount);

        // Check entity queries
        List<Asset> resultList = entityRepository.findByName("Asset_1");
        logger.info(">>>> entityRepository.findByName >>>");
        resultList.stream().forEach(p->logger.info(" ==== " + p));
        logger.info("---------------------------------------------------------");

        resultList = entityRepository.findByAssetName("Asset");
        logger.info(">>>> entityRepository.findByAssetName >>>");
        resultList.stream().forEach(p->logger.info(" ==== " + p));
        logger.info("---------------------------------------------------------");

        Integer testAssetID = testAssets.get(0).getAssetID();
        Asset result = entityRepository.findByAssetID(testAssetID);
        logger.info(">>>> entityRepository.findByProjectNo(testAssetID) >>>");
        logger.info(" ==== " + result);
        logger.info("---------------------------------------------------------");

        resultList = entityRepository.findAll();
        logger.info(">>>> entityRepository.findAll >>>");
        resultList.stream().forEach(p->logger.info(" ==== " + p));
        logger.info("---------------------------------------------------------");

        // Clean all entities
        // tearDown();
    }

    @Test @Order(2) @Disabled
    public void testAssetCount() {
        // Check CDI Beans (business services)
        logger.info("Repository implementation object:: " + entityRepository);
        logger.info("Repository implementation class:: " + entityRepository.getClass().getName());

        Long projectCount = entityRepository.count();
        logger.info("projectCount = " + projectCount);
    }

    public void tearDown() throws Exception {
        Long assetCount = entityRepository.count();
        logger.info("projectCount before cleaning = " + assetCount);

        entityRepository.deleteAll();

        assetCount = entityRepository.count();
        logger.info("projectCount after cleaning = " + assetCount);
    }
}
