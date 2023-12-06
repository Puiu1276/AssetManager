package org.scrum.domain.asset;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor

public class AssetDestinatieLocatieView {
    @NonNull private Integer assetId;
    @NonNull private String assetName;

    @NonNull private String destinatieLocatie;

    @Override
    public String toString() {
        return "AssetCurrentLocationView{" +
                "assetId=" + assetId +
                ", assetName='" + assetName + '\'' +
                ", destinatieLocatie='" + destinatieLocatie + '\'' +
                '}';
    }
}
