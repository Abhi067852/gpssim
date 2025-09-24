package com.fleet.demo.service;

import com.fleet.demo.model.dto.GeoLocation;
import com.fleet.demo.model.dto.VehicleLocation;
import com.fleet.demo.model.entity.Vehicle;
import com.fleet.demo.model.enums.Status;
import com.fleet.demo.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    LocationProducerService locationProducerService;

    public Vehicle getVehicleById(String vehicleId){
        return vehicleRepository.findByvehicleId(vehicleId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Vehicle with ID " + vehicleId + " not found"
        ));
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        vehicle.setStatus(Status.PARKED);
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(String vehicleId,Status status) {
        Vehicle vehicle = getVehicleById(vehicleId);

        vehicle.setStatus(status);

        return vehicleRepository.save(vehicle);
    }

    public String  updateDestination(VehicleLocation vehicleLocation){
        System.out.println("vehicle location: "+ vehicleLocation.getVehicleId());
        Vehicle vehicle= getVehicleById(vehicleLocation.getVehicleId());
        System.out.println(vehicle.getDestination());
        vehicle.setStatus(Status.RUNNING);
        vehicle.setDestination(GeoLocation.builder()
                .longitude(vehicleLocation.getVehicleLocation().getLongitude())
                .latitude(vehicleLocation.getVehicleLocation().getLatitude())
                .build());
        vehicleRepository.save(vehicle);
        return "Vehicle Saved";
    }
}
