package org.scrum.domain.location;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LocationCategoryConverter implements
        AttributeConverter<LocationCategory, String> {

    @Override
    public String convertToDatabaseColumn(LocationCategory attributeValue) {
        if (attributeValue.equals(LocationCategory.BIROU))
            return "Birou_Location";
        if (attributeValue.equals(LocationCategory.DEPOZIT))
            return "Depozit_Location";
        return null;
    }

    @Override
    public LocationCategory convertToEntityAttribute(String dbData) {
        if (dbData.equals("Birou_Location"))
            return LocationCategory.BIROU;
        if (dbData.equals("Depozit_Location"))
            return LocationCategory.DEPOZIT;
        return null;
    }

}
