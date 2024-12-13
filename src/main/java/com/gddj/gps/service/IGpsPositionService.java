package com.gddj.gps.service;

import com.gddj.gps.dto.GpsPositionDto;

import java.util.List;

public interface IGpsPositionService {

    /**
     *
     * @param gpsPositionDto - GpsPositionDto Object
     */
    void createPosition(GpsPositionDto gpsPositionDto);

    /**
     *
     * @return a list of all the GpsPositionDto
     */
    List<GpsPositionDto> fetchGpsPositionList();

    /**
     *
     * @param positionId - id of the gps position
     * @return boolean indicating if the delete of the gps position is successful or not
     */
    boolean deletePosition(Long positionId);

    /**
     *
     * @param positionId1
     * @param positionId2
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean isDistanceLess(Long positionId1, Long positionId2);
}
