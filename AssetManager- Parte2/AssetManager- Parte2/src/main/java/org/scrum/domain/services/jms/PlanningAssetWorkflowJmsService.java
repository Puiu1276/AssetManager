package org.scrum.domain.services.jms;

import jakarta.jms.Message;
import org.scrum.domain.asset.AssetDestinatieLocatieView;
import org.scrum.domain.services.IPlanningAssetWorkflowService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class PlanningAssetWorkflowJmsService {
    private static Logger logger = Logger.getLogger(PlanningAssetWorkflowJmsService.class.getName());

    private JmsTemplate jmsTemplateToSend;

    private IPlanningAssetWorkflowService workflowService;
    private MessageConverter jacksonJmsMessageConverter;
    // SBT Default Dependency Injection (Autowired for all prms)
    public PlanningAssetWorkflowJmsService(JmsTemplate jmsTemplateToSend,
                                           IPlanningAssetWorkflowService workflowService,
                                             MessageConverter jacksonJmsMessageConverter) {
        this.jmsTemplateToSend = jmsTemplateToSend;
        this.workflowService = workflowService;
        this.jacksonJmsMessageConverter = jacksonJmsMessageConverter;
    }
    @JmsListener(destination = "WorkflowService::planNewAsset", containerFactory = "connectionFactory")
    public void planNewAsset(Message message) throws Exception{
        // Decode DTO from Message
        AssetWorkflowDTO assetDTO = (AssetWorkflowDTO) jacksonJmsMessageConverter.fromMessage(message);
        logger.info("Received <" + assetDTO + ">");
        // Call business method
        Integer assetID = workflowService.planNewAsset(assetDTO.getName());
        // Return call: send response with DTO
        jmsTemplateToSend.convertAndSend(message.getJMSReplyTo(), assetID);
    }

    @JmsListener(destination = "WorkflowService::planNewAsset", containerFactory = "connectionFactory")
    public void planDestinationLocation(Message message) throws Exception{
        // Decode DTO from Message
        AssetLocationWorkflowDTO locationDTO = (AssetLocationWorkflowDTO) jacksonJmsMessageConverter.fromMessage(message);
        logger.info("Received <" + locationDTO + ">");
        // Call business method
        Integer locationID = workflowService.planDestinationLocation(locationDTO.getAssetID(), locationDTO.getDestinatieLocatie());
        // Return call: send response with DTO
        jmsTemplateToSend.convertAndSend(message.getJMSReplyTo(), locationID);
    }

    @JmsListener(destination = "WorkflowService::getProjectSummaryData", containerFactory = "connectionFactory")
    public void getAssetSummaryData(Message message) throws Exception{
        // Decode DTO from Message
        Integer assetIdDTO = (Integer) jacksonJmsMessageConverter.fromMessage(message);
        logger.info("Received <" + assetIdDTO + ">");
        // Call business method
        AssetDestinatieLocatieView assetDestinatieLocatieView = workflowService.getAssetSummaryData(assetIdDTO);
        // Return call: send response with DTO
        jmsTemplateToSend.convertAndSend(message.getJMSReplyTo(), assetDestinatieLocatieView);
    }

}
