package org.scrum.domain.asset;
import java.io.Serializable;
import java.util.Objects;

public class Asset implements Comparable<Asset>, Serializable {
    private static final long serialVersionUID = 1L;

    private Integer assetID;
    private String name;
    private String location; // Locul de depozitare al activului
    private AssetCategory category;

    // Constructor fara parametri
    public Asset() {
        super();
    }

    // Constructor cu toti parametrii
    public Asset(Integer assetID, String name, String location, AssetCategory category) {
        this.assetID = assetID;
        this.name = name;
        this.location = location;
        this.category = category;
    }

    // Getters si setters
    public Integer getAssetID() {
        return assetID;
    }

    public void setAssetID(Integer assetID) {
        this.assetID = assetID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public AssetCategory getCategory() {
        return category;
    }

    public void setCategory(AssetCategory category) {
        this.category = category;
    }

    // Metoda compareTo pentru sortare dupa nume
    @Override
    public int compareTo(Asset other) {
         return this.getName().compareTo(((Asset)obj).getName());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asset asset = (Asset) o;
        return Objects.equals(assetID, asset.assetID) &&
                Objects.equals(name, asset.name) &&
                Objects.equals(location, asset.location) && category == asset.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetID, name, location, category);
    }

    // Metoda toString pentru a reprezenta informatiile despre Asset sub forma de string
    @Override
    public String toString() {
        return "Asset [assetID=" + assetID + ", name=" + name + ", location=" + location + ", category=" + category + "]";
    }

}

