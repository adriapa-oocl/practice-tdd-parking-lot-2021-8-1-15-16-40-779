package com.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy{

    private List<ParkingLot> multipleParkingLot = new ArrayList<>();

    public ParkingBoy(List<ParkingLot> parkingLot) {
        this.multipleParkingLot = parkingLot;
    }

    public ParkingBoy(ParkingLot parkingLot) {
        this.multipleParkingLot.add(parkingLot);
    }

    public List<ParkingLot> getMultipleParkingLot() {
        return multipleParkingLot;
    }

    public ParkingTicket park(Car car) {
        return getAvailableParkingLot().park(car);
    }

    private ParkingLot getAvailableParkingLot(){
        return multipleParkingLot
                .stream()
                .filter(ParkingLot::isAvailable)
                .findFirst()
                .orElseThrow(ExcessParkingLotCapacity::new);
    }

    public Car fetch(ParkingTicket parkingTicket) {
//        return multipleParkingLot.fetch(parkingTicket);
        return null;
    }
}
