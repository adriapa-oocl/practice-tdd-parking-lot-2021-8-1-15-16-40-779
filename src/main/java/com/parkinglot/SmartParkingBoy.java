package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLots){
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        return getAvailableSmartParkingLot().park(car);
    }

    private ParkingLot getAvailableSmartParkingLot(){
        return getParkingLots()
                .stream()
                .filter(parkingLot -> !parkingLot.isFullCapacity())
                .min(Comparator.comparing(ParkingLot::getParkingSlotCount))
                .orElseThrow(ExcessParkingLotCapacity::new);
    }
}
