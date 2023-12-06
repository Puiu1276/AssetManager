package org.scrum.domain.operatiune;

import org.scrum.domain.asset.Asset;

public class Transfer extends Opeariune {


    public Transfer transferAsset(Asset assetID, String locatieCurenta, String destinatieLocatie) {
        if (!checkIfAssetExists(assetID)) {
            throw new RuntimeException("Asset with ID " + assetID + " does not exist.");
        }
        // Crează o nouă operație de transfer
        Transfer transferOperation = new Transfer();
        transferOperation.setTipOpeariune(OperatiuneTip.TRANSFER);
        transferOperation.setDescriereOperatiune("Transfer of asset " + assetID + " from " + locatieCurenta + " to " + destinatieLocatie);
        transferOperation.setAssetOperat(assetID);
        transferOperation.setLocatieCurenta(locatieCurenta);
        transferOperation.setDestinatieLocatie(destinatieLocatie);

        return transferOperation;

    }

    private boolean checkIfAssetExists(Asset assetID) {
        return true;
    }
}
