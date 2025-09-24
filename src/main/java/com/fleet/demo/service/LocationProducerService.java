package com.fleet.demo.service;

import com.fleet.demo.model.dto.GeoLocation;
import com.fleet.demo.model.dto.VehicleLocation;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class LocationProducerService {

    private final double speedMetersPerSec = 40_000.0 / 3600.0; // 40 km/h = 11.11 m/s
    private double traveledDistance = 0.0; // meters
    private final double totalDistance=0.0;    // meters

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

        public GeoLocation getNextLocation(GeoLocation base, GeoLocation destination) {
            double totalDistance = haversineDistance(base, destination);
            if (traveledDistance >= totalDistance) {
                return destination; // already reached
            }

            // move by distance covered in 2 seconds
            traveledDistance += speedMetersPerSec * 2;
            if (traveledDistance > totalDistance) {
                traveledDistance = totalDistance; // cap at destination
            }

            // fraction of journey completed
            double fraction = traveledDistance / totalDistance;

            double latitude = base.getLatitude() +
                    fraction * (destination.getLatitude() - base.getLatitude());
            double longitude = base.getLongitude() +
                    fraction * (destination.getLongitude() - base.getLongitude());

            latitude = roundToFourDecimalPlaces(latitude);
            longitude = roundToFourDecimalPlaces(longitude);
            System.out.println("Current location: " + latitude+ ", "+ longitude);
            return new GeoLocation(latitude, longitude);
        }

        private double roundToFourDecimalPlaces(double value) {
            return BigDecimal.valueOf(value)
                    .setScale(4, RoundingMode.HALF_UP)
                    .doubleValue();
        }

        // Haversine formula for distance (in meters)
        private double haversineDistance(GeoLocation a, GeoLocation b) {
            final int EARTH_RADIUS = 6371000; // meters

            double lat1 = Math.toRadians(a.getLatitude());
            double lon1 = Math.toRadians(a.getLongitude());
            double lat2 = Math.toRadians(b.getLatitude());
            double lon2 = Math.toRadians(b.getLongitude());

            double dLat = lat2 - lat1;
            double dLon = lon2 - lon1;

            double h = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                    + Math.cos(lat1) * Math.cos(lat2)
                    * Math.sin(dLon / 2) * Math.sin(dLon / 2);

            double c = 2 * Math.atan2(Math.sqrt(h), Math.sqrt(1 - h));

            return EARTH_RADIUS * c;

    }



}
