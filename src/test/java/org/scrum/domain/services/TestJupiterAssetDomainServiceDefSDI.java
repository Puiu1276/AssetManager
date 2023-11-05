package org.scrum.domain.services;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.logging.Logger;

// JUnit 5 with Spring Boot Test
@SpringBootTest
public class TestJupiterAssetDomainServiceDefSDI {
    private static Logger logger = Logger.getLogger(TestJupiterAssetDomainServiceDefSDI.class.getName());

    @Autowired
    private IAssetEntityFactory assetService;

    @Autowired
    private IAssetEntityRepository assetRepository;

    @Autowired // @Qualifier("assetFactory")
    private IAssetEntityFactory assetFactory;

    @Test
    public void testAssetDomainServiceIntegration() {
        logger.info("Asset Repository implementation object: " + assetRepository);
        logger.info("Asset Repository implementation class: " + assetRepository.getClass().getName());

        logger.info("Asset Factory implementation object: " + assetFactory);
        logger.info("Asset Factory implementation class: " + assetFactory.getClass().getName());

        logger.info("Asset Service implementation object: " + assetService);
        logger.info("Asset Service implementation class: " + assetService.getClass().getName());

        Integer assetCount = assetService.getAssetCount();
        assertTrue(assetCount > 0, "Assets are not being counted...");
        logger.info("Asset count from service: " + assetCount);
    }

    @Autowired @Qualifier("AssetEntityFactoryBaseCDI")
    private IAssetEntityFactory qualifiedAssetFactory;

    @Test
    public void testQualifiedAssetEntityFactory() {
        // Here we would check something specific to the qualified asset factory if needed
        Integer assetCounts = qualifiedAssetFactory.getAssetRepository().size();
        assertTrue(assetCounts > 0, "Assets are not being counted...");
        logger.info("Qualified Project count: " + assetCounts);
    }
}

