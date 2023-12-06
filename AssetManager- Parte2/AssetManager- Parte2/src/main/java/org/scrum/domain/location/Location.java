package org.scrum.domain.location;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.scrum.domain.asset.Asset;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Location implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue
    private Integer locationID;
    private String locationName;
    private String country;
    private String city;
    private String adress;

    @Convert(converter = LocationCategoryConverter.class)
    private LocationCategory category;

    @ManyToOne
    private Asset asset;

    public Location(Integer locationID, String locationName, String country,
                    String city, String adress) {
        this.locationID = locationID;
        this.locationName = locationName;
        this.country = country;
        this.city = city;
        this.adress = adress;
    }

    public Location(String locationName, String country,
                    String city, String adress) {
        this.locationName = locationName;
        this.country = country;
        this.city = city;
        this.adress = adress;
        this.category = LocationCategory.DEPOZIT;
    }


    @Override
    public String toString() {
        return "Location{" +
                "locationID=" + locationID +
                ", name='" + locationName + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", adress='" + adress + '\'' +
                ", asset=" + asset +
                '}';
    }

    public void addLocatieCurenta(String locatieCurenta) {
        String locationName;
    }

    public void addDestinatieLocatie(String destinatieLocatie) {
        String locationName;
    }


    public void setLocatieCurenta(Location locatieCurenta) {

    }

    public void setDestinatieLocatie(Location destinatieLocatie) {

    }

}
