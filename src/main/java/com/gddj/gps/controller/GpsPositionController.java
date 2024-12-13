package com.gddj.gps.controller;

import com.gddj.gps.constants.GpsPositionConstants;
import com.gddj.gps.dto.ErrorResponseDto;
import com.gddj.gps.dto.GpsPositionDto;
import com.gddj.gps.dto.ResponseDto;
import com.gddj.gps.service.IGpsPositionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class GpsPositionController {

    @Autowired
    private IGpsPositionService iGpsPositionService;

    @Operation(
            summary = "Create a gps position",
            description = "REST API to create a new gps position"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createGpsPosition(@Valid @RequestBody GpsPositionDto gpsPositionDto) {
        iGpsPositionService.createPosition(gpsPositionDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(GpsPositionConstants.STATUS_201, GpsPositionConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch a list of GPS positions",
            description = "REST API to fetch the whole list of gps positions"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<List<GpsPositionDto>> fetchCGpsPositionList() {
        List<GpsPositionDto> gpsPositionDto = iGpsPositionService.fetchGpsPositionList();
        return ResponseEntity.status(HttpStatus.OK).body(gpsPositionDto);
    }

    @Operation(
            summary = "Delete a gps position",
            description = "REST API to delete a position by its id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteGpsPosition(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9])", message = "id must be digits")
                                                            String positionId) {
        boolean isDeleted = iGpsPositionService.deletePosition(Long.parseLong(positionId));
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(GpsPositionConstants.STATUS_200, GpsPositionConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(GpsPositionConstants.STATUS_417, GpsPositionConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Distance between two gps positions",
            description = "Get a boolean value to know if two gps positions are less than 10km apart"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetchDistance")
    public ResponseEntity<Boolean> isDistanceLessThan10km(@RequestParam
                                          @Pattern(regexp = "(^$|[0-9])", message = "Id number must be digits")
                                          String positionId1,
                                          @RequestParam
                                          @Pattern(regexp = "(^$|[0-9])", message = "Id number must be digits")
                                          String positionId2) {
        boolean isDistanceLessThan10km = iGpsPositionService.isDistanceLess(Long.parseLong(positionId1), Long.parseLong(positionId2));
        return ResponseEntity.status(HttpStatus.OK).body(isDistanceLessThan10km);
    }
}
