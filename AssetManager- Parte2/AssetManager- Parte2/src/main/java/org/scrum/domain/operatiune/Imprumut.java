package org.scrum.domain.operatiune;

import org.scrum.domain.asset.Asset;

public class Imprumut extends Opeariune{

    public Imprumut imprumutAsset(Asset assetID) {
        if (!checkIfAssetExists(assetID)) {
            throw new RuntimeException("Asset with ID " + assetID + " does not exist.");
        }
        // Crează o nouă operație de transfer
        Imprumut imprumutOperation = new Imprumut();
        imprumutOperation.setTipOpeariune(OperatiuneTip.IMPRUMUT);
        imprumutOperation.setDescriereOperatiune("Imprumutul activului " + assetID);
        imprumutOperation.setAssetOperat(assetID);

        // Aici poți adăuga logica pentru a salva operația în baza de date sau în alt sistem de stocare

        return imprumutOperation;

    }

    private boolean checkIfAssetExists(Asset assetID) {
        return true;
    }
}
