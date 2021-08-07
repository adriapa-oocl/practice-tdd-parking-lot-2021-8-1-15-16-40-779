package com.parkinglot;

public class ExcessParkingLotCapacity extends RuntimeException{
    @Override
    public String getMessage() {
        return "No Available Position.";
    }
}
