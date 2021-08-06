package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void should_return_parking_ticket_when_park_given_a_parking_lot() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingLot.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_parked_car_when_fetch_given_a_parking_lot_and_a_parked_car_and_a_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        //when
        Car actualCar = parkingLot.fetch(parkingTicket);

        //then
        assertEquals(car, actualCar);
    }

    @Test
    void should_return_the_right_car_when_fetch_twice_given_a_parking_lot_with_two_parked_cars_and_two_parking_tickets() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car jesseCar = new Car();
        Car robertCar = new Car();
        ParkingTicket jesseParkingTicket = parkingLot.park(jesseCar);
        ParkingTicket robertParkingTicket = parkingLot.park(robertCar);

        //when
        Car actualCarJesse = parkingLot.fetch(jesseParkingTicket);
        Car actualCarRobert = parkingLot.fetch(robertParkingTicket);

        //then
        assertEquals(jesseCar, actualCarJesse);
        assertEquals(robertCar, actualCarRobert);
    }

    @Test
    void should_return_nothing_when_fetch_given_a_parking_lot_and_a_parked_car_but_wrong_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket dummyParkingTicket = new ParkingTicket();

        //when
        Car actualCar = parkingLot.fetch(dummyParkingTicket);

        //then
        assertNull(actualCar);

    }

    @Test
    void should_return_nothing_when_fetch_given_a_parking_lot_and_a_parked_car_but_ticket_already_used() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        ParkingTicket parkingTicket = parkingLot.park(car);

        //when
        Car reUsedParkingTicket = parkingLot.fetch(parkingTicket);

        //then
        assertNull(reUsedParkingTicket);
    }
}
