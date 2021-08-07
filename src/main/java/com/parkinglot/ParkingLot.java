package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private final int DEFAULT_CAPACITY = 10;
    private final int CAPACITY;

    private Map<ParkingTicket, Car> parkingSlotPosition = new HashMap<>();

    public ParkingLot(int parkingLotCapacity){
        this.CAPACITY = parkingLotCapacity;
    }

    public ParkingLot(){
        CAPACITY = DEFAULT_CAPACITY;

    }

    public Map<ParkingTicket, Car> getParkingSlotPosition() {
        return parkingSlotPosition;
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

    public boolean isFullCapacity(){
        return parkingSlotPosition.size() >= CAPACITY;
    }

    public boolean isSlotAvailable(){
        return parkingSlotPosition.size() < CAPACITY;
    }

    public boolean isRelated(ParkingTicket parkingTicket) {
        return parkingSlotPosition.containsKey(parkingTicket);
    }

    public int getParkingSlotCount(){
        return parkingSlotPosition.size();
    }

    public float getParkingLotAvailableSlot(){
        return (float)(CAPACITY - getParkingSlotCount()) / CAPACITY;
    }
}
