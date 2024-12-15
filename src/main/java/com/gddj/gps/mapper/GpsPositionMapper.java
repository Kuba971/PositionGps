package com.gddj.gps.mapper;

import com.gddj.gps.dto.GpsPositionDto;
import com.gddj.gps.entity.GpsPositionEntity;

public class GpsPositionMapper {
    public static GpsPositionDto mapToGpsPositionDto(GpsPositionEntity gpsPositionEntity) {
        GpsPositionDto gpsPositionDto = new GpsPositionDto();
        gpsPositionDto.setPositionId(String.valueOf(gpsPositionEntity.getPositionId()));
        gpsPositionDto.setPositionName(gpsPositionEntity.getPositionName());
        gpsPositionDto.setPositionLongitude(gpsPositionEntity.getPositionLongitude());
        gpsPositionDto.setPositionLatitude(gpsPositionEntity.getPositionLatitude());
        return gpsPositionDto;
    }

    public static GpsPositionEntity mapToGpsPositionEntity(GpsPositionDto gpsPositionDto) {
        GpsPositionEntity gpsPositionEntity = new GpsPositionEntity();
        gpsPositionEntity.setPositionName(gpsPositionDto.getPositionName());
        gpsPositionEntity.setPositionLongitude(gpsPositionDto.getPositionLongitude());
        gpsPositionEntity.setPositionLatitude(gpsPositionDto.getPositionLatitude());
        return gpsPositionEntity;
    }
}
