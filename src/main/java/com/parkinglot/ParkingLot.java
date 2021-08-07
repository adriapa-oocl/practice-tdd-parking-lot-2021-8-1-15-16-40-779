package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private static int PARKING_LOT_CAPACITY = 10;
    private Map<ParkingTicket, Car> parkingSlotPosition = new HashMap<>();

    public ParkingLot(int capacity){
        PARKING_LOT_CAPACITY = capacity;
    }

    public ParkingLot(){

    }

    public ParkingTicket park(Car car){
        if (isFullCapacity()){
            throw new ExcessParkingLotCapacity();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingSlotPosition.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket){
        if (isUnrecognizedParkingTicket(parkingTicket)){
            throw new UnrecognizedParkingTicketException();
        }
        final Car car = parkingSlotPosition.get(parkingTicket);
        parkingSlotPosition.remove(parkingTicket);
        return car;
    }

    private boolean isUnrecognizedParkingTicket(ParkingTicket parkingTicket) {
        return !parkingSlotPosition.containsKey(parkingTicket);
    }

    private boolean isFullCapacity(){
        return PARKING_LOT_CAPACITY == parkingSlotPosition.size();
    }

}
