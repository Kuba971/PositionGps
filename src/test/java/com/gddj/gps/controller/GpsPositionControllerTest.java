package com.gddj.gps.controller;

import com.gddj.gps.constants.GpsPositionConstants;
import com.gddj.gps.dto.GpsPositionDto;
import com.gddj.gps.dto.ResponseDto;
import com.gddj.gps.entity.GpsPositionEntity;
import com.gddj.gps.mapper.GpsPositionMapper;
import com.gddj.gps.service.impl.GpsPositionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GpsPositionControllerTest {

    @Mock
    private GpsPositionServiceImpl gpsPositionService;

    @InjectMocks
    private GpsPositionController gpsPositionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFetchCGpsPositionList() {
        List<GpsPositionDto> mockGpsPositions = List.of(
                GpsPositionMapper.mapToGpsPositionDto(new GpsPositionEntity(1111111L, "Position1", 12.971598, 77.594566)),
                        GpsPositionMapper.mapToGpsPositionDto(new GpsPositionEntity(2222222L, "Position2", 13.082680, 80.270718))
                );

        when(gpsPositionService.fetchGpsPositionList()).thenReturn(mockGpsPositions);

        ResponseEntity<List<GpsPositionDto>> response = gpsPositionController.fetchCGpsPositionList();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(mockGpsPositions, response.getBody());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());

        verify(gpsPositionService, times(1)).fetchGpsPositionList();
    }

    @Test
    void createGpsPosition() {
        GpsPositionDto gpsPositionDto = GpsPositionMapper.mapToGpsPositionDto(new GpsPositionEntity(1111111L, "Position1", 12.971598, 77.594566));

        doNothing().when(gpsPositionService).createPosition(gpsPositionDto);

        ResponseEntity<ResponseDto> response = gpsPositionController.createGpsPosition(gpsPositionDto);

        assertEquals(201, response.getStatusCode().value());
        assertEquals(GpsPositionConstants.STATUS_201, Objects.requireNonNull(response.getBody()).getStatusCode());
        assertEquals(GpsPositionConstants.MESSAGE_201, response.getBody().getStatusMsg());

        verify(gpsPositionService, times(1)).createPosition(gpsPositionDto);
    }

    @Test
    void deleteGpsPosition() {
        Long positionId = 1111111L;
        boolean isDeleted = true;
        when(gpsPositionService.deletePosition(positionId)).thenReturn(isDeleted);

        ResponseEntity<ResponseDto> response = gpsPositionController.deleteGpsPosition(positionId.toString());

        assertEquals(200, response.getStatusCode().value());
        assertEquals(GpsPositionConstants.STATUS_200, Objects.requireNonNull(response.getBody()).getStatusCode());
        assertEquals(GpsPositionConstants.MESSAGE_200, response.getBody().getStatusMsg());

        verify(gpsPositionService, times(1)).deletePosition(positionId);
    }

    @Test
    void isDistanceLessThan10km() {
        Long positionId1 = 1L;
        Long positionId2 = 6L;
        String rawDataFront = "{\"positionId1\":\"1\",\"positionId2\":\"6\"}";

        when(gpsPositionService.isDistanceLess(positionId1, positionId2)).thenReturn(false);

        ResponseEntity<Boolean> response = gpsPositionController.isDistanceLessThan10km(rawDataFront);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(false, response.getBody());

        verify(gpsPositionService, times(1)).isDistanceLess(positionId1, positionId2);
    }
}

