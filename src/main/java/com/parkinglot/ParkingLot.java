package com.parkinglot;

public class ParkingLot {

    private Car car;

    public ParkingTicket park(Car car) {
        this.car = car;
        return car;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return null;
    }
}
