package com.protonmail.landrevillejf.cognos.categories.api.kafka;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@SuppressWarnings("CheckStyle")
@Profile("production")
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "category-events", groupId = "your-consumer-group-id")
    public void listenToCategoryEvents(String message) {
        // Process the received message
        System.out.println("Received message: " + message);
    }
}
