package com.gddj.gps.service.impl;

import com.gddj.gps.Exception.GpsPositionNotFoundException;
import com.gddj.gps.dto.GpsPositionDto;
import com.gddj.gps.entity.GpsPositionEntity;
import com.gddj.gps.mapper.GpsPositionMapper;
import com.gddj.gps.repository.GpsPositionRepository;
import com.gddj.gps.service.IGpsPositionService;
import com.gddj.gps.utils.DistanceHelper;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GpsPositionServiceImpl implements IGpsPositionService {

    private GpsPositionRepository gpsPositionRepository;

    @Override
    public void createPosition(GpsPositionDto customerDto) {
        GpsPositionEntity gpsPositionEntity = GpsPositionMapper.mapToGpsPositionEntity(customerDto);
        gpsPositionRepository.save(gpsPositionEntity);
    }

    @Override
    public List<GpsPositionDto> fetchGpsPositionList() {
        return gpsPositionRepository.findAll().stream()
                .map(GpsPositionMapper::mapToGpsPositionDto).toList();
    }


    @Override
    public boolean deletePosition(Long positionId) {
        GpsPositionEntity gpsPositionEntity = gpsPositionRepository.findById(positionId).orElseThrow(
                () -> new GpsPositionNotFoundException("GpsPosition", "Id", positionId)
        );
        gpsPositionRepository.deleteById(gpsPositionEntity.getPositionId());
        return true;
    }

    @Override
    public boolean isDistanceLess(Long positionId1, Long positionId2) {
        GpsPositionEntity position1 = gpsPositionRepository.findById(positionId1).orElseThrow(
                () -> new GpsPositionNotFoundException("GpsPosition", "Id", positionId1)
        );
        GpsPositionEntity position2 = gpsPositionRepository.findById(positionId2).orElseThrow(
                () -> new GpsPositionNotFoundException("GpsPosition", "Id", positionId2)
        );
        return (DistanceHelper.distanceBetweenTwoPointsInMeters(position1.getPositionLatitude(),
                position2.getPositionLatitude(),
                position1.getPositionLongitude(),
                position2.getPositionLongitude(), 0, 0) < 10000)
            ;
    }
}
