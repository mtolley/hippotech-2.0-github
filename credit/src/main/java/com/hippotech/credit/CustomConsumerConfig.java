package com.hippotech.credit;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class CustomConsumerConfig {
    Logger logger = LoggerFactory.getLogger(CustomConsumerConfig.class);
    public CustomConsumerConfig() {
        logger.info("Constructor::CustomConsumerConfig");
    }

    @Bean
    public ConsumerFactory<String,String> consumerFactory(){
        Map<String,Object> configMap = new HashMap<>();
        //Kafka is accessible at localhost:9092 on our host machine
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"kafka:9092");
        //group_id is needed in the @KafkaListener
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG,"group_id");
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configMap);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}