package org.scrum.domain.services.jms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AssetOperationWorkflowDTO {
    private Integer assetID;
    private String denumireOperatiune;
    private String memberName;
}
