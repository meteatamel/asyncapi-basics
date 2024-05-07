package com.asyncapi;

  
 
import com.asyncapi.model.UserSignedUpPayload;
 

import org.eclipse.paho.client.mqttv3.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Example of tests for mqtt based on testcontainers library
 */
@Generated(value="com.asyncapi.generator.template.spring", date="2024-05-07T15:15:21.744Z")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestcontainerMqttTest {

    
    @Value("${mqtt.topic.receiveUserSignedUp}")
    private String receiveUserSignedUpPublishTopic;
    

    @ClassRule
    public static GenericContainer mosquitto = new GenericContainer("eclipse-mosquitto").withExposedPorts(1883);
    
    private IMqttClient publisher;

    @DynamicPropertySource
    public static void mqttProperties(DynamicPropertyRegistry registry) {
        String address = "tcp://" + mosquitto.getContainerIpAddress() + mosquitto.getMappedPort(1883);
        registry.add("mqtt.broker.address", () -> address);
    }

    @BeforeEach
    public void before() throws MqttException {
        String address = "tcp://" + mosquitto.getContainerIpAddress() + mosquitto.getMappedPort(1883);
        publisher = new MqttClient(address, UUID.randomUUID().toString());
        publisher.connect();
    }

    @AfterEach
    public void after() throws MqttException {
        publisher.disconnect();
    }

      
    @Test
    public void receiveUserSignedUpConsumerTestcontainers() throws Exception {
        UserSignedUpPayload payload = new UserSignedUpPayload();

        sendMessage(receiveUserSignedUpPublishTopic, payload.toString().getBytes());

        Thread.sleep(1_000);
    }
    
    
    
    protected void sendMessage(String topic, byte[] message) throws Exception {
        publisher.publish(topic, new MqttMessage(message));
    }
    
}
