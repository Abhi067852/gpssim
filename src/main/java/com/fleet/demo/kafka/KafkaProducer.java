package com.fleet.demo.kafka;

import com.fleet.demo.dto.GeoLocation;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String,String> kafkaTemplate;
    public void sendLocation(GeoLocation location){
        String message= "Latitude: "+location.latitude()+ "Longitude: "+ location.longitude();
        kafkaTemplate.send("location-topic",message);
        System.out.println("message sent: " + message);
    }
}
