package com.fleet.demo.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.demo.model.dto.GeoLocation;
import com.fleet.demo.model.dto.VehicleLocation;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    public void sendLocation(VehicleLocation vehicleLocation) {
        try {
            kafkaTemplate.send("location-topic", objectMapper.writeValueAsString(vehicleLocation));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("message sent: " + vehicleLocation.getVehicleId());
    }
}
