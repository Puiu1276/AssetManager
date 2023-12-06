package org.scrum.domain.asset;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.scrum.domain.angajati.Responsabil;
import org.scrum.domain.location.Location;
import org.scrum.domain.operatiune.Opeariune;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Asset implements Serializable, Comparable<Asset>{
    @EqualsAndHashCode.Include
    @Min(1) @NotNull(message = " ProjectNo is required!")
    @Id
    @GeneratedValue
    private Integer assetID;
    private AssetType AssetType;
    private Integer valoareAsset;
    @NotNull(message = " Asset Name is required!")
    @Size(min=1, message = " Asset must have an explicit name!")
    private String assetName;

    @OneToOne(cascade = CascadeType.ALL, fetch = EAGER, orphanRemoval = false)
    private Opeariune opeariune;

    @OneToOne(cascade = CascadeType.ALL, fetch = EAGER, orphanRemoval = false)
    private Location currentLocation;

    @OneToOne(cascade = CascadeType.ALL, fetch = EAGER, orphanRemoval = false)
    private Location destinatieLocatie;

    @OneToMany(mappedBy="asset",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private List<Responsabil> responsabil = new ArrayList<>();

    @Convert(converter = AssetGroupConverter.class)
    private AssetGroup assetGroup;

    @Override
    public String toString() {
        return "Asset{" +
                "assetID=" + assetID +
                ", AssetType=" + AssetType +
                ", valoareAsset=" + valoareAsset +
                ", assetName='" + assetName + '\'' +
                ", opeariune=" + opeariune +
                ", currentLocation=" + currentLocation +
                ", destinatieLocatie=" + destinatieLocatie +
                ", responsabil=" + responsabil +
                ", OperatiuniCount=" + OperatiuniCount +
                '}';
    }

    @Override
    public int compareTo(Asset other) {
        return this.assetID.compareTo(other.getAssetID());
    }

    @Transient
    protected Integer OperatiuniCount = 0;

    @PrePersist
    public void onPrePersist() {
        System.out.println(">>> JPA Triggers: @PrePersist");
        this.setOperatiuniCount((this.responsabil==null)? 0 : this.responsabil.size());
    }

    @PreUpdate
    public void onPreUpdate() {
        System.out.println(">>> JPA Triggers: @PreUpdate");
        this.setOperatiuniCount((this.responsabil==null)? 0 : this.responsabil.size());
    }

    @PreRemove
    public void onPreRemove() {
        System.out.println(">>> JPA Triggers: @PreRemove");
    }

    public void addResponsabil (String memberName){
        this.responsabil.add(new Responsabil(null, memberName));
    }

    public void add(Asset testAsset) {
    }


    public enum AssetType {IMOBILIZARICORPORALE, STOCURI};

}
