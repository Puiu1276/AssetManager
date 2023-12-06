package org.scrum.domain.operatiune;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.scrum.domain.angajati.Responsabil;
import org.scrum.domain.asset.Asset;

import java.util.Date;

import static jakarta.persistence.FetchType.EAGER;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Opeariune {
    @EqualsAndHashCode.Include
    @Min(1) @NotNull(message = " Operatiune ID is required!")
    @Id
    @GeneratedValue
    private Integer operatiuneID;
    private OperatiuneTip tipOpeariune;
    private String descriereOperatiune;
    private String locatieCurenta;
    private String destinatieLocatie;
    @NotNull(message = " OpeariuneDate is required!")
    @Temporal(TemporalType.DATE)
    private Date operatiuneDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = EAGER, orphanRemoval = false)
    private Responsabil responsabilOpeariune;

    @OneToOne(cascade = CascadeType.ALL, fetch = EAGER, orphanRemoval = false)
    private Asset assetOperat;

    @Override
    public String toString() {
        return "Opeariune{" +
                "operatiuneID=" + operatiuneID +
                ", descriereOperatiune='" + descriereOperatiune + '\'' +
                ", operatiuneDate=" + operatiuneDate +
                ", responsabilOpeariune=" + responsabilOpeariune +
                ", assetOperat=" + assetOperat +
                '}';
    }

    protected enum OperatiuneTip {
        IMPRUMUT, TRANSFER;
    }
}
