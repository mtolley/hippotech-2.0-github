package com.hippotech.credit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsConsumer {
    private final Logger logger = LoggerFactory.getLogger(CustomerDetailsConsumer.class);

    @KafkaListener(topics = "creditcard", groupId = "group_id")
    public void customerDetailConsumer(String message){
        logger.info("#### -> Running credit check");
        ProcessBuilder builder = new ProcessBuilder();
        builder.command("touch", message);

        try {
            Process process = builder.start();
            int exitCode = process.waitFor();
        } catch (Exception e) {

        }
    }
}