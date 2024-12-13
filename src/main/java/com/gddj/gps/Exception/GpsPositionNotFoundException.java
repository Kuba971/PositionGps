package com.gddj.gps.Exception;

public class GpsPositionNotFoundException extends RuntimeException{
    public GpsPositionNotFoundException(String name, String fieldName, Long fieldValue) {
        super(String.format("%s not found with the given input data %s : '%s'", name, fieldName, fieldValue));
    }
}
