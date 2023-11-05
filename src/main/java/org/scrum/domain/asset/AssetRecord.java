package org.scrum.domain.asset;

import lombok.Builder;
import lombok.With;

import java.util.Objects;

@Builder(toBuilder = true) @With
public record AssetRecord(Integer assetID, String name) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AssetRecord that = (AssetRecord) o;

        return Objects.equals(assetID, that.assetID);
    }

    @Override
    public int hashCode() {
        return assetID != null ? assetID.hashCode() : 0;
    }

    static void test(){
        AssetRecord data = AssetRecord.builder().assetID(1).name("P1").build();
    }
}
