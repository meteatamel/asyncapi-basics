package com.asyncapi.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import javax.annotation.processing.Generated;
import java.util.HashMap;
import java.util.Map;

@Generated(value="com.asyncapi.generator.template.spring", date="2024-05-07T15:15:21.842Z")
@Service
public class PublisherServiceImpl implements PublisherService {
    
        
    

    private String replaceParameters(String topic, Map<String, String> parameters) {
        if (parameters != null) {
            String compiledTopic = topic;
            for (String key : parameters.keySet()) {
                compiledTopic = compiledTopic.replace("{" + key + "}", parameters.get(key));
            }
            return compiledTopic;
        }
        return topic;
    }
}
