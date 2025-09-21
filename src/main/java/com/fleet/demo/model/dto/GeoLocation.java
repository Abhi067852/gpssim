package com.fleet.demo.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class GeoLocation {
    double latitude;
    double longitude;

}
