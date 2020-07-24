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
    void should__when_given_() {
        //given

        //when

        //then


    }

    @Test
    void should_return_no_car_when_fetch_given_tick_used() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket parkingTicket = new ParkingTicket(true, null);
        //when
        Car car = parkingBoy.fetch(parkingTicket);
        //then
        assertEquals(null, car.getLicenseNumber());
    }

    @Test
    void should_return_no_car_when_fetch_given_null() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when
        Car car = parkingBoy.fetch(null);
        //then
        assertEquals(null, car.getLicenseNumber());
    }
}
