package org.scrum.domain.services.jms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AssetLocationWorkflowDTO {
    private Integer assetID;
    private String locatieCurenta;
    private String destinatieLocatie;
}
