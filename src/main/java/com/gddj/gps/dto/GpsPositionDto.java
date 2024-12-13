package com.gddj.gps.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class GpsPositionDto {
    private String positionId;
    private String positionName;
    private Double positionLatitude;
    private Double positionLongitude;
}
