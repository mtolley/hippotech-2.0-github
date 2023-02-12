package com.hippotech.approval;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsProducer {
    public static final Logger logger = LoggerFactory.getLogger(CustomerDetailsProducer.class);
    public static final String TOPIC = "creditcard";

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    public CustomerDetailsProducer() {
        logger.info("Constructor::CustomerDetailsProducer");
    }


    public void sendMessage(String message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}