package org.scrum.domain.services;

import org.scrum.domain.asset.AssetDestinatieLocatieView;
import org.scrum.domain.operatiune.Opeariune;

public interface IPlanningAssetWorkflowService {
    Integer planNewAsset(String assetName);

    Integer addResponsabilToAsset(Integer assetID, Integer memberID, String memberName);

    Integer planDestinationLocation(Integer assetId, String destinatieLocatie);

    // (4) Get project summary data: ProjectCurrentReleaseView
    AssetDestinatieLocatieView getAssetSummaryData(Integer assetID);

}
