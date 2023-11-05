package org.scrum.domain.asset;

// Enum pentru categoriile de Asset
public enum AssetCategory {
    IMOBILIZARI_CORPORALE("Imobilizari Corporale"),
    STOCURI("Stocuri");

    public final String displayValue;

    AssetCategory(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
