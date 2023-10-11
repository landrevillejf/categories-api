package com.protonmail.landrevillejf.cognos.categories.api.kafka;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@SuppressWarnings("CheckStyle")
@Profile("production")
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String key, Object value) {
        kafkaTemplate.send(topic, key, value);
    }
}
