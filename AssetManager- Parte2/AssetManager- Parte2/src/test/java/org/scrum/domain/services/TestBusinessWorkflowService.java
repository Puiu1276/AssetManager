package org.scrum.domain.services;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.scrum.domain.asset.AssetDestinatieLocatieView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
 * WORKFLOW Business Service Tests
 * Strategy: Simple-Facade
 *
 */
//JUnit.5
@SpringBootTest
// @Transactional
public class TestBusinessWorkflowService {
	private static Logger logger = Logger.getLogger(TestBusinessWorkflowService.class.getName());

	/* Simple-Facade Business Service
	 * Injection from scanning
	 * @Service("PlanningProjectBusinessWorkflowServiceImpl")
	 * Bean-implementation class
	 */
	@Autowired
	private IPlanningAssetWorkflowService planningAssetBusinessWorkflowFacadeService;

	@Test
	public void test_WorkflowServiceFacade() {
		logger.info("Domain Service implementation instance:: " + planningAssetBusinessWorkflowFacadeService);
		logger.info("Dpmain Service implementation class:: "
					+ planningAssetBusinessWorkflowFacadeService.getClass().getName());
        // 1.
		Integer assetID = planningAssetBusinessWorkflowFacadeService.planNewAsset("Planned.Test.JProject");
		// 2.
		planningAssetBusinessWorkflowFacadeService.addResponsabilToAsset(assetID, 1, "Planned.Test");
		planningAssetBusinessWorkflowFacadeService.addResponsabilToAsset(assetID, 2, "Planned.Test");
		planningAssetBusinessWorkflowFacadeService.addResponsabilToAsset(assetID, 3, "Planned.Test");
		// 3.
        String destinatieLocatie = new String(new String());
		planningAssetBusinessWorkflowFacadeService.planDestinationLocation(assetID, destinatieLocatie);
		// 4.
		AssetDestinatieLocatieView viewData = planningAssetBusinessWorkflowFacadeService.getAssetSummaryData(assetID);
		//
		logger.info(viewData.toString());
	}
}
