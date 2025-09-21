package com.fleet.demo.service;

import com.fleet.demo.model.entity.Vehicle;
import com.fleet.demo.model.enums.Status;
import com.fleet.demo.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

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
}
