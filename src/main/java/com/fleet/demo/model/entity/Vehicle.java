package com.fleet.demo.model.entity;

import com.fleet.demo.model.dto.GeoLocation;
import com.fleet.demo.model.enums.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Vehicle")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class Vehicle {
    @Id
    String id;
    String vehicleId;
    String vehicleName;
    String startDate;
    Status status;
    GeoLocation vehicleLocation;
    String driverName;
    Integer fuelLevel;
}
