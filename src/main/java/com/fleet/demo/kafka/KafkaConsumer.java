package com.fleet.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(topics = "location-topic", groupId = "location-group")
    public void consume(String message) {
        System.out.println("📥 Received: " + message);
    }

}
