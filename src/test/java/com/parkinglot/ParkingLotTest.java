package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

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
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingTicket dummyParkingTicket = new ParkingTicket();

        //when
//        Car actualCar = parkingLot.fetch(dummyParkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(dummyParkingTicket));

        //then
//        assertNull(actualCar);
        assertEquals("Unrecognized parking ticket.", exception.getMessage());

    }

    @Test
    void should_return_nothing_when_fetch_given_a_parking_lot_and_a_parked_car_but_ticket_already_used() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = parkingLot.park(new Car());
        parkingLot.fetch(parkingTicket);

        //when
//        Car actualCar = parkingLot.fetch(parkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(parkingTicket));

        //then
//        assertNull(actualCar);
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_not_return_a_parking_ticket_given_a_car_and_parking_lot_but_no_space() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        List<ParkingTicket> parkingTicket = new LinkedList<>();

        for (int i = 0; i < 10 ; i++) {
            parkingTicket.add(parkingLot.park(car));
        }

        //when
        Exception exception = assertThrows(ExcessParkingLotCapacity.class, () -> parkingLot.park(car));
//        ParkingTicket excessParkingTicket = parkingLot.park(car);

        //then
//        assertNull(excessParkingTicket);
        assertEquals("No Available Position.", exception.getMessage());
    }

    @Test
    void should_throw_exception_with_error_message_when_fetch_given_a_parking_lot_and_an_unrecognized_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(unrecognizedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_throw_exception_with_error_message_when_fetch_given_a_parking_lot_and_a_used_parking_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket usedParkingTicket = parkingLot.park(new Car());
        parkingLot.fetch(usedParkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetch(usedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_throw_exception_with_error_message_when_park_given_a_parking_lot_but_no_capacity_nor_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        List<ParkingTicket> parkingTicket = new LinkedList<>();

        for (int i = 0; i < 10 ; i++) {
            parkingTicket.add(parkingLot.park(car));
        }

        //when
        Exception exception = assertThrows(ExcessParkingLotCapacity.class, () -> parkingLot.park(car));

        //then
        assertEquals("No Available Position.", exception.getMessage());
    }

    @Test
    void should_return_a_parking_ticket_when_park_the_car_given_a_parking_lot_standard_parking_boy_and_car() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_a_car_when_fetch_given_a_parking_lot_with_a_parked_car_standard_parking_boy_and_parking_ticket() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //when
        Car actualCar = parkingBoy.fetch(parkingTicket);

        //then
        assertEquals(car, actualCar);
    }

    @Test
    void should_return_correct_car_with_correct_ticket_given_a_parking_lot_with_cars_standard_parking_boy_and_tickets() {
        //given
        Car jesseCar = new Car();
        Car robertCar = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket jesseParkingTicket = parkingBoy.park(jesseCar);
        ParkingTicket robertParkingTicket = parkingBoy.park(robertCar);

        //when
        Car actualCarJesse = parkingBoy.fetch(jesseParkingTicket);
        Car actualCarRobert = parkingBoy.fetch(robertParkingTicket);

        //then
        assertEquals(jesseCar, actualCarJesse);
        assertEquals(robertCar, actualCarRobert);
    }

    @Test
    void should_return_error_message_when_fetch_given_a_parking_lot_standard_parking_boy_and_wrong_parking_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(unrecognizedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }


}
