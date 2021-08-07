package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
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

    @Test
    void should_return_error_message_when_fetch_given_a_parking_lot_standard_parking_boy_and_used_parking_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        parkingBoy.fetch(parkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(parkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_error_message_when_fetch_given_a_parking_lot_a_parking_boy_but_no_parking_slot_or_car() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        Car car = new Car();
        List<ParkingTicket> parkingTicket = new LinkedList<>();

        for (int i = 0; i < 10 ; i++) {
            parkingTicket.add(parkingBoy.park(car));
        }

        //when
        Exception exception = assertThrows(ExcessParkingLotCapacity.class, () -> parkingBoy.park(car));

        //then
        assertEquals("No Available Position.", exception.getMessage());
    }

    @Test
    void should_return_ticket_and_park_car_to_first_parking_lot_when_park_given_a_standard_parking_boy_with_two_parking_lots_and_both_available_and_a_car() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));

        //when
        ParkingTicket parkingTicket = parkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
        assertTrue(parkingBoy.getParkingLots().get(0).getParkingSlotPosition().containsKey(parkingTicket));
    }

    @Test
    void should_return_ticket_and_park_car_to_second_parking_lot_when_park_given_a_standard_parking_boy_first_parking_lot_is_full_and_a_car() {
        //given
        Car car = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        List<ParkingTicket> parkingTicket = new LinkedList<>();

        for (int i = 0; i < 10 ; i++) {
            parkingTicket.add(parkingBoy.park(car));
        }

        //when
        ParkingTicket secondParkingLotTicket = parkingBoy.park(car);

        //then
        assertTrue(parkingBoy.getParkingLots().get(1).getParkingSlotPosition().containsKey(secondParkingLotTicket));
    }

    @Test
    void should_return_right_car_with_each_ticket_when_fetch_given_standard_parking_boy_parking_lots_parked_cars_and_parking_tickets() {
        Car jesseCar = new Car();
        Car robertCar = new Car();
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
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
    void should_return_error_message_when_given_a_standard_parking_boy_two_parking_lots_unrecognized_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(unrecognizedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_error_message_when_given_a_standard_parking_boy_two_parking_lots_used_ticket() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        parkingBoy.fetch(parkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(parkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void return_error_message_when_park_given_a_standard_parking_boy_two_parking_lots_no_available_parking_slot() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        Car car = new Car();
        List<ParkingTicket> parkingLotOneTicket = new LinkedList<>();
        List<ParkingTicket> parkingLotTwoTicket = new LinkedList<>();

        for (int i = 0; i < 10 ; i++) {
            parkingLotOneTicket.add(parkingBoy.park(car));
        }
        for (int i = 0; i < 10 ; i++) {
            parkingLotTwoTicket.add(parkingBoy.park(car));
        }

        //when
        Exception exception = assertThrows(ExcessParkingLotCapacity.class, () -> parkingBoy.park(car));

        //then
        assertEquals("No Available Position.", exception.getMessage());
    }

    @Test
    void should_return_ticket_when_park_given_a_smart_parking_boy_and_parking_lot_available() {
        //given
        ParkingBoy smartParkingBoy = new ParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        List<ParkingTicket> parkingLotOneTicket = new LinkedList<>();
        List<ParkingTicket> parkingLotTwoTicket = new LinkedList<>();
        Car car = new Car();

        //when
        for (int i = 0; i < 7 ; i++) {
            parkingLotOneTicket.add(smartParkingBoy.park(car));
        }
        for (int i = 0; i <= 6 ; i++) {
            parkingLotTwoTicket.add(smartParkingBoy.park(car));
        }

        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //then
        assertEquals(5, smartParkingBoy.getParkingLots().get(1).getParkingSlotCount());
        assertTrue(smartParkingBoy.getParkingLots().get(0).getParkingSlotCount() > smartParkingBoy.getParkingLots().get(1).getParkingSlotCount());
    }

    @Test
    void should_return_car_when_fetch_given_a_smart_parking_boy_and_parking_lot_and_a_car() {
        //given
        Car car = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = smartParkingBoy.park(car);

        //when
        Car actualCar = smartParkingBoy.fetch(parkingTicket);

        //then
        assertEquals(car, actualCar);
    }

    @Test
    void should_return_car_when_fetch_given_a_smart_parking_boy_two_parked_cars_and_parking_lot() {
        //given
        Car jesseCar = new Car();
        Car robertCar = new Car();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        ParkingTicket jesseParkingTicket = smartParkingBoy.park(jesseCar);
        ParkingTicket robertParkingTicket = smartParkingBoy.park(robertCar);

        //when
        Car actualJesseCar = smartParkingBoy.fetch(jesseParkingTicket);
        Car actualRobertCar = smartParkingBoy.fetch(robertParkingTicket);

        //then
        assertEquals(jesseCar, actualJesseCar);
        assertEquals(robertCar, actualRobertCar);
    }

    @Test
    void should_return_error_message_when_given_a_smart_parking_boy_two_parking_lots_unrecognized_ticket() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(unrecognizedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_error_message_when_given_a_smart_parking_boy_two_parking_lots_used_ticket() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        ParkingTicket parkingTicket = smartParkingBoy.park(new Car());
        smartParkingBoy.fetch(parkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(parkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void return_error_message_when_park_given_a_smart_parking_boy_two_parking_lots_no_available_parking_slot() {
        //given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        Car car = new Car();
        List<ParkingTicket> parkingLotOneTicket = new LinkedList<>();
        List<ParkingTicket> parkingLotTwoTicket = new LinkedList<>();

        for (int i = 0; i < 10 ; i++) {
            parkingLotOneTicket.add(smartParkingBoy.park(car));
        }
        for (int i = 0; i < 10 ; i++) {
            parkingLotTwoTicket.add(smartParkingBoy.park(car));
        }

        //when
        Exception exception = assertThrows(ExcessParkingLotCapacity.class, () -> smartParkingBoy.park(car));

        //then
        assertEquals("No Available Position.", exception.getMessage());
    }

    @Test
    void should_return_car_given_super_smart_parking_boy_a_parking_lot_and_car() {
        //given
        Car car = new Car();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());

        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    void should_return_car_when_fetch_given_super_smart_parking_boy_and_parking_lot_and_car() {
        //given
        Car car = new Car();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);

        //when
        Car actualCar = superSmartParkingBoy.fetch(parkingTicket);

        //then
        assertEquals(car, actualCar);
    }

    @Test
    void should_return_car_when_fetch_given_super_smart_parking_boy_and_two_parked_cars_and_parking_lot() {
        //given
        Car jesseCar = new Car();
        Car robertCar = new Car();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        ParkingTicket jesseParkingTicket = superSmartParkingBoy.park(jesseCar);
        ParkingTicket robertParkingTicket = superSmartParkingBoy.park(robertCar);

        //when
        Car actualJesseCar = superSmartParkingBoy.fetch(jesseParkingTicket);
        Car actualRobertCar = superSmartParkingBoy.fetch(robertParkingTicket);

        //then
        assertEquals(jesseCar, actualJesseCar);
        assertEquals(robertCar, actualRobertCar);
    }

    @Test
    void should_return_error_message_when_given_a_super_smart_parking_boy_two_parking_lots_unrecognized_ticket() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        ParkingTicket unrecognizedParkingTicket = new ParkingTicket();

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> superSmartParkingBoy.fetch(unrecognizedParkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_return_error_message_when_given_a_super_smart_parking_boy_two_parking_lots_used_ticket() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        ParkingTicket parkingTicket = superSmartParkingBoy.park(new Car());
        superSmartParkingBoy.fetch(parkingTicket);

        //when
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> superSmartParkingBoy.fetch(parkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void return_error_message_when_park_given_a_super_smart_parking_boy_two_parking_lots_no_available_parking_slot() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        Car car = new Car();
        List<ParkingTicket> parkingLotOneTicket = new LinkedList<>();
        List<ParkingTicket> parkingLotTwoTicket = new LinkedList<>();

        for (int i = 0; i < 10 ; i++) {
            parkingLotOneTicket.add(superSmartParkingBoy.park(car));
        }
        for (int i = 0; i < 10 ; i++) {
            parkingLotTwoTicket.add(superSmartParkingBoy.park(car));
        }

        //when
        Exception exception = assertThrows(ExcessParkingLotCapacity.class, () -> superSmartParkingBoy.park(car));

        //then
        assertEquals("No Available Position.", exception.getMessage());
    }

    @Test
    void return_ticket_when_park_given_super_smart_parking_boy_2_parking_slot_with_10_car_but_not_higher_than_20() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(new ParkingLot(), new ParkingLot()));
        Car car = new Car();
        List<ParkingTicket> parkingTicket = new LinkedList<>();

        //when
        for (int i = 0; i < 20 ; i++) {
            parkingTicket.add(superSmartParkingBoy.park(car));
        }
        Exception exception = assertThrows(ExcessParkingLotCapacity.class, () -> superSmartParkingBoy.park(car));

        //then
        assertEquals("No Available Position.", exception.getMessage());
    }

    @Test
    void return_ticket_when_park_given_super_smart_parking_boy_has_multiple_parking_lot_and_many_available_slots() {
        //given
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(new ParkingLot(10), new ParkingLot(100)));
        Car car = new Car();
        List<ParkingTicket> parkingTicket = new LinkedList<>();

        //when
        for (int i = 0; i < 20 ; i++) {
            parkingTicket.add(superSmartParkingBoy.park(car));
        }

        //then
        assertEquals(2, superSmartParkingBoy.getParkingLots().get(0).getParkingSlotCount());
        assertEquals(18, superSmartParkingBoy.getParkingLots().get(1).getParkingSlotCount());
        assertTrue(superSmartParkingBoy.getParkingLots().get(0).getParkingSlotCount() < superSmartParkingBoy.getParkingLots().get(1).getParkingSlotCount());
    }
}
