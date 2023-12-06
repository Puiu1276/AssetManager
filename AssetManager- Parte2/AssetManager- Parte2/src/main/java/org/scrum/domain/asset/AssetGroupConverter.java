package org.scrum.domain.asset;

import jakarta.persistence.AttributeConverter;

public class AssetGroupConverter implements
        AttributeConverter<AssetGroup, String> {
    @Override
    public String convertToDatabaseColumn(AssetGroup attribute) {
        if (attribute != null) {
            System.out.println(">>> convertToDatabaseColumn: " + attribute.getGroupName() + ";");
            return attribute.getGroupName() + ";";
        }
        return null;    }

    @Override
    public AssetGroup convertToEntityAttribute(String dbData) {
        if (dbData != null) {
            String[] sqlData = dbData.split(";");
            if (sqlData != null && sqlData.length > 0)
                return new AssetGroup(sqlData[0]);
        }
        return null;    }
}
