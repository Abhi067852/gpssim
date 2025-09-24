package com.fleet.demo.scheduler;

import com.fleet.demo.model.dto.GeoLocation;
import com.fleet.demo.kafka.KafkaProducer;
import com.fleet.demo.model.dto.VehicleLocation;
import com.fleet.demo.model.entity.Vehicle;
import com.fleet.demo.model.enums.Status;
import com.fleet.demo.repository.VehicleRepository;
import com.fleet.demo.service.LocationProducerService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class LocationScheduler {
    private final LocationProducerService locationProducerService;
    private final KafkaProducer kafkaProducer;
    private final VehicleRepository vehicleRepository;
    private static final GeoLocation base = new GeoLocation(28.4494, 77.0518);

    @Scheduled(fixedRate = 2000)
    public void sendLocation(){
        List<Vehicle> vehicles = vehicleRepository.findByStatus(Status.RUNNING);

        vehicles.forEach(vehicle -> {
            //GeoLocation location= locationProducerService.RandomLocation(vehicle.getVehicleLocation());
            GeoLocation current = vehicle.getVehicleLocation() != null
                    ? vehicle.getVehicleLocation()
                    : base;
            GeoLocation location = locationProducerService.getNextLocation(current,vehicle.getDestination());
            vehicle.setVehicleLocation(location);
            VehicleLocation vehicleLocation =
                    VehicleLocation.builder().vehicleId(vehicle.getVehicleId()).vehicleLocation(location).build();
            kafkaProducer.sendLocation(vehicleLocation);
            if(location.equals(vehicle.getDestination())){
                vehicle.setStatus(Status.PARKED);
            }
            vehicleRepository.save(vehicle);
        });

    }





}
