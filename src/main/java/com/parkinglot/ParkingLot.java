package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private static int FULL_CAPACITY = 10;
    private Map<ParkingTicket, Car> parkingSlotPosition = new HashMap<>();

    public ParkingLot(int capacity){
        FULL_CAPACITY = capacity;
    }

    public ParkingLot(){

    }

    public ParkingTicket park(Car car){
        if (ifFullCapacity()){
            return  null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingSlotPosition.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket){
        final Car car = parkingSlotPosition.get(parkingTicket);
        parkingSlotPosition.remove(parkingTicket);
        return car;
    }

    private boolean ifFullCapacity(){
        return FULL_CAPACITY == parkingSlotPosition.size();
    }

}
