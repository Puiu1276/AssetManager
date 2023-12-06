package org.scrum.domain.services.jms;

import jakarta.jms.Message;
import org.junit.jupiter.api.Test;
import org.scrum.domain.asset.AssetDestinatieLocatieView;
import org.scrum.domain.location.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

import java.util.logging.Logger;

@SpringBootTest
public class TestPlanningAssetWorkflowJmsService {
    private static Logger logger = Logger.getLogger(TestPlanningAssetWorkflowJmsService.class.getName());
    //
    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    MessageConverter jacksonJmsMessageConverter;

    @Test
    void testJmsService() throws Exception{
        // 1 WorkflowServices::planNewProject
        AssetWorkflowDTO assetDTO = new AssetWorkflowDTO("Planned.Test.JMS-Project", "Iasi");
        logger.info("Sending DTO from Jms Client: " + assetDTO);
        Message received = jmsTemplate.sendAndReceive("WorkflowServices::planNewAsset",
                session -> {
                    Message message = jacksonJmsMessageConverter.toMessage(assetDTO, session);
                    message.setJMSReplyTo(session.createTemporaryQueue());
                    return message;
                });
        Integer assetID = (Integer) jacksonJmsMessageConverter.fromMessage(received);
        logger.info("Replied to client assetID: " + assetID);

        // 2
        ResponsabilWorkFlowDTO responsabilDTO_A = new ResponsabilWorkFlowDTO(assetID,"Test.Membru_A");
        received = jmsTemplate.sendAndReceive("WorkflowServices::addResponsabil",
                session -> {
                    Message message = jacksonJmsMessageConverter.toMessage(responsabilDTO_A, session);
                    message.setJMSReplyTo(session.createTemporaryQueue());
                    return message;
                });
        Integer operatiuneId = (Integer) jacksonJmsMessageConverter.fromMessage(received);
        logger.info("Replied to client operatiuneId: " + operatiuneId);
        //
        ResponsabilWorkFlowDTO responsabilDTO_B = new ResponsabilWorkFlowDTO(assetID,"Test.Membru_B");
        received = jmsTemplate.sendAndReceive("WorkflowServices::addResponsabil",
                session -> {
                    Message message = jacksonJmsMessageConverter.toMessage(responsabilDTO_B, session);
                    message.setJMSReplyTo(session.createTemporaryQueue());
                    return message;
                });
        operatiuneId = (Integer) jacksonJmsMessageConverter.fromMessage(received);
        logger.info("Replied to client operatiuneId: " + operatiuneId);
        //
        ResponsabilWorkFlowDTO responsabilDTO_C = new ResponsabilWorkFlowDTO(assetID,"Test.Membru_C");
        received = jmsTemplate.sendAndReceive("WorkflowServices::addResponsabil",
                session -> {
                    Message message = jacksonJmsMessageConverter.toMessage(responsabilDTO_C, session);
                    message.setJMSReplyTo(session.createTemporaryQueue());
                    return message;
                });
        operatiuneId = (Integer) jacksonJmsMessageConverter.fromMessage(received);
        logger.info("Replied to client operatiuneId: " + operatiuneId);

        // 3 WorkflowServices::planDestinationLocation
        String locatieCurenta = new String(new String());
        String destinatieLocatie = new String(new String());
        AssetLocationWorkflowDTO assetLocationDTO = new AssetLocationWorkflowDTO(assetID, locatieCurenta, destinatieLocatie);
        logger.info("Sending DTO from Jms Client: " + assetLocationDTO);
        received = jmsTemplate.sendAndReceive("WorkflowServices::planDestinationLocation",
                session -> {
                    Message message = jacksonJmsMessageConverter.toMessage(assetLocationDTO, session);
                    message.setJMSReplyTo(session.createTemporaryQueue());
                    return message;
                });
        operatiuneId = (Integer) jacksonJmsMessageConverter.fromMessage(received);
        logger.info("Replied to client releaseId: " + operatiuneId);

        // 4 WorkflowServices::getProjectSummaryData
        logger.info("Sending DTO from Jms Client: " + assetID);
        received = jmsTemplate.sendAndReceive("WorkflowServices::getAssetSummaryData",
                session -> {
                    Message message = jacksonJmsMessageConverter.toMessage(assetID, session);
                    message.setJMSReplyTo(session.createTemporaryQueue());
                    return message;
                });
        AssetDestinatieLocatieView viewData = (AssetDestinatieLocatieView) jacksonJmsMessageConverter.fromMessage(received);
        logger.info("Replied to client viewData: " + viewData);
    }

}

