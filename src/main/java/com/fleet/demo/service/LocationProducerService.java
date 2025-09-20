package com.fleet.demo.service;

import com.fleet.demo.dto.GeoLocation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class LocationProducerService {

    public GeoLocation RandomLocation(){
        double latitude = ThreadLocalRandom.current().nextDouble(-90.0, 90.0);
        double longitude = ThreadLocalRandom.current().nextDouble(-180.0,180.0);
        latitude = roundToTwoDecimalPlaces(latitude);
        longitude = roundToTwoDecimalPlaces(longitude);
        return new GeoLocation(latitude,longitude);
    }
    private double roundToTwoDecimalPlaces(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
