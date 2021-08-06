package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private Map<ParkingTicket, Car> parkingSlotPosition = new HashMap<>();

    public ParkingTicket park(Car car) {
        if(parkingSlotPosition.size() > 10){
            return null;
        }
        else{
            ParkingTicket parkingTicket = new ParkingTicket();
            parkingSlotPosition.put(parkingTicket, car);
            return parkingTicket;
        }
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(parkingSlotPosition.get(parkingTicket) != null){
            parkingSlotPosition.remove(parkingTicket);
        }
        return parkingSlotPosition.get(parkingTicket);
    }

}
