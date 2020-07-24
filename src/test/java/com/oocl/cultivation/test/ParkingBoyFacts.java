package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParkingBoyFacts {



    @Test
    void should_return_ticket_when_park_given_car() {
        //given
        Car car = new Car("C12345");
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when
        ParkingTicket ticket = parkingBoy.park(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetch_given_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = new ParkingTicket();
        //when
        Car car =parkingBoy.fetch(parkingTicket);
        //then
        assertNotNull(car);
    }

    @Test
    void should_return_no_car_when_fetch_given_tick_used() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = new ParkingTicket(true,null);
        //when
        Car car = parkingBoy.fetch(parkingTicket);
        //then
        assertEquals(null,car.getLicenseNumber());
    }

    @Test
    void should_return_no_car_when_fetch_given_null() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when
        Car car = parkingBoy.fetch(null);
        //then
        assertEquals(null,car.getLicenseNumber());
    }
}
