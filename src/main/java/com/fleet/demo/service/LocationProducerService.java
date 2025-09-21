package com.fleet.demo.service;

import com.fleet.demo.model.dto.GeoLocation;
import com.fleet.demo.model.dto.VehicleLocation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class LocationProducerService {

    public GeoLocation RandomLocation(GeoLocation vehicleLocation){
        double latitude =
                vehicleLocation.getLatitude()+ ThreadLocalRandom.current().nextDouble(0,
                0.0305 );
        double longitude =
                vehicleLocation.getLongitude() +ThreadLocalRandom.current().nextDouble(0,
                        0.1005);
        latitude = roundToTwoDecimalPlaces(latitude);
        longitude = roundToTwoDecimalPlaces(longitude);
        return new GeoLocation(latitude,longitude);
    }
    private double roundToTwoDecimalPlaces(double value) {
        return BigDecimal.valueOf(value)
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
