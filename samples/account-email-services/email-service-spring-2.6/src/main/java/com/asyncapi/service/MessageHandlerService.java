package com.asyncapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.integration.mqtt.support.MqttHeaders;

import com.asyncapi.model.UserSignedUpPayload;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(value="com.asyncapi.generator.template.spring", date="2024-05-07T15:15:21.809Z")
@Service
public class MessageHandlerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageHandlerService.class);


        
        
    
    public void handleReceiveUserSignedUp(Message<?> message) {
        String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
        receiveUserSignedUp((UserSignedUpPayload) message.getPayload());
    }
    
    public void receiveUserSignedUp(UserSignedUpPayload payload) {
        LOGGER.info("handler user/signedup");
        LOGGER.info(String.valueOf(payload.toString()));
    }
    


}
