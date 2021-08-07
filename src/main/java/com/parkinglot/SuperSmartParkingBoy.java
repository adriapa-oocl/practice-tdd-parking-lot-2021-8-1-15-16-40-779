package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy{

    public SuperSmartParkingBoy(List<ParkingLot> parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public ParkingTicket park(Car car){
        return getAvailableSuperSmartParkingLot().park(car);
    }

    private ParkingLot getAvailableSuperSmartParkingLot(){
        return getParkingLots()
                .stream()
                .filter(parkingLot -> !parkingLot.isFullCapacity())
                .max(Comparator.comparing(ParkingLot::getParkingLotAvailableSlot))
                .orElseThrow(ExcessParkingLotCapacity::new);
    }

}
