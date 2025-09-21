package com.fleet.demo.repository;

import com.fleet.demo.model.entity.Vehicle;
import com.fleet.demo.model.enums.Status;
import org.apache.kafka.common.metrics.Stat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle,String> {
    Optional<Vehicle> findByvehicleId(String vehicleId);

    List<Vehicle> findByStatus(Status status);

}
