package com.fleet.demo.scheduler;

import com.fleet.demo.dto.GeoLocation;
import com.fleet.demo.kafka.KafkaProducer;
import com.fleet.demo.service.LocationProducerService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LocationScheduler {
    private final LocationProducerService locationProducerService;
    private final KafkaProducer kafkaProducer;

    @Scheduled(fixedRate = 2000)
    public void sendLocation(){
        GeoLocation location= locationProducerService.RandomLocation();
        kafkaProducer.sendLocation(location);
    }
}
