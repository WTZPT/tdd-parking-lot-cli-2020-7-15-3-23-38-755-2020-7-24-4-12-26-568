package com.oocl.cultivation.exception;

public class ParkingException extends RuntimeException{

    private String errorMessage;

    public ParkingException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
}
