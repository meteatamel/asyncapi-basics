package com.asyncapi.infrastructure;

import com.asyncapi.service.MessageHandlerService;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.util.StringUtils;

import javax.annotation.processing.Generated;

@Generated(value="com.asyncapi.generator.template.spring", date="2024-05-07T15:15:21.897Z")
@Configuration
public class Config {

    @Value("${mqtt.broker.address}")
    private String address;

    @Value("${mqtt.broker.timeout.connection}")
    private int connectionTimeout;

    @Value("${mqtt.broker.timeout.disconnection}")
    private long disconnectionTimeout;

    @Value("${mqtt.broker.timeout.completion}")
    private long completionTimeout;

    @Value("${mqtt.broker.clientId}")
    private String clientId;

    @Value("${mqtt.broker.username}")
    private String username;

    @Value("${mqtt.broker.password}")
    private String password;

    

    
    @Value("${mqtt.topic.receiveUserSignedUp}")
    private String receiveUserSignedUpTopic;
    

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        
        
        
        
        options.setServerURIs(new String[] { address });
        if (!StringUtils.isEmpty(username)) {
            options.setUserName(username);
        }
        if (!StringUtils.isEmpty(password)) {
            options.setPassword(password.toCharArray());
        }
        options.setConnectionTimeout(connectionTimeout);
        factory.setConnectionOptions(options);
        return factory;
    }

    @Autowired
    MessageHandlerService messageHandlerService;

    
    @Bean
    public IntegrationFlow receiveUserSignedUpFlow() {
        return IntegrationFlow.from(receiveUserSignedUpInbound())
                .handle(messageHandlerService::handleReceiveUserSignedUp)
                .get();
    }

    @Bean
    public MessageProducerSupport receiveUserSignedUpInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(clientId,
                mqttClientFactory(), receiveUserSignedUpTopic);
        adapter.setCompletionTimeout(connectionTimeout);
        adapter.setDisconnectCompletionTimeout(disconnectionTimeout);
        adapter.setConverter(new DefaultPahoMessageConverter());
        return adapter;
    }
    

    

}
