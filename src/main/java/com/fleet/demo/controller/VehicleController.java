package com.fleet.demo.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fleet.demo.model.dto.VehicleLocation;
import com.fleet.demo.model.entity.Vehicle;
import com.fleet.demo.model.enums.Status;
import com.fleet.demo.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.manager.StatusManagerServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
@Tag(name = "Vehicle Controller", description = "CRUD operations for vehicles")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;
//
//    @PutMapping("/{id}")
//    @Operation(summary = "Update an existing vehicle")
//    public Vehicle updateVehicle(@PathVariable String id, @RequestParam Status status) throws JsonMappingException {
//        return vehicleService.updateVehicle(id,status);
//    }
    @PostMapping("/create")
    public Vehicle createVehicle(@RequestBody Vehicle vehicle){
        return  vehicleService.saveVehicle(vehicle);
    }

    @PostMapping("/destination")
    @Operation(summary = "Send destination location")
    public String vehicleDestination(@RequestBody VehicleLocation vehicleLocation){
        return vehicleService.updateDestination(vehicleLocation);
    }

}
