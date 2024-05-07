package com.asyncapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;
import java.util.Random;

@Generated(value="com.asyncapi.generator.template.spring", date="2024-05-07T15:15:21.795Z")
@Component
public class CommandLinePublisher implements CommandLineRunner {

    @Autowired
    PublisherService publisherService;

    @Override
    public void run(String... args) {
        System.out.println("******* Sending message: *******");
        System.out.println("Message sent");
    }
}
