package org.scrum.domain.asset;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class AssetData {
    private Integer assetID;
    private String name;

    static void test(){
        AssetData data = AssetData.builder().assetID(1).name("P1").build();
    }
}
