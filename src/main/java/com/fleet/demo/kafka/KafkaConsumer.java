package com.fleet.demo.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.demo.model.dto.GeoLocation;
import com.fleet.demo.model.entity.Vehicle;
import com.fleet.demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    VehicleService vehicleService;


    @KafkaListener(topics = "vehicle-topic", groupId = "vehicle-group")
    public void consume(String message) throws JsonProcessingException {
        Vehicle vehicle = objectMapper.readValue(message, Vehicle.class);
        vehicleService.saveVehicle(vehicle);
    }

}
