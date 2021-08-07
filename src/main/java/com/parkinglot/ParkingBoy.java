package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy{

    private List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy(List<ParkingLot> parkingLot) {
        this.parkingLots = parkingLot;
    }

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public ParkingTicket park(Car car) {
        return getAvailableParkingLot().park(car);
    }

    private ParkingLot getAvailableParkingLot(){
        return parkingLots
                .stream()
                .filter(ParkingLot::isSlotAvailable)
                .findFirst()
                .orElseThrow(ExcessParkingLotCapacity::new);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return findParkingLot(parkingTicket).fetch(parkingTicket);
    }

    private ParkingLot findParkingLot(ParkingTicket parkingTicket) {
        return parkingLots
                .stream()
                .filter(parkingLot -> parkingLot.isRelated(parkingTicket))
                .findFirst()
                .orElseThrow(UnrecognizedParkingTicketException::new)
                ;
    }
}
