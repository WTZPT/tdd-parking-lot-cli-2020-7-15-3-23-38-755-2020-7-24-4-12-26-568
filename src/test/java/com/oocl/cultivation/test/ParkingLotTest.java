package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {

    @Test
    @DisplayName("story1-ac2 The parking lot can accept park multiple cars  ")
    void should_return_quantity_10_when_park_given_10_cars() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        List<Car> cars = new ArrayList<>();
        for(int i = 0 ; i <10; i++) {
            cars.add(new Car("c"+i));
        }
        //when
         for (Car car : cars){
             parkingLot.park(car);
         }
        //then
        assertEquals(10,parkingLot.getSpots().size());
    }

    @Test
    @DisplayName("story1-ac2  Give the parking ticket back to the parking lot to fetch the car.")
    void should_validate_car_ticket_when_fetch_given_two_cars() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car carA = new Car("A");
        Car carB = new Car("B");
        ParkingTicket ticketA = parkingLot.park(carA);
        ParkingTicket ticketB = parkingLot.park(carB);
        //when
        Car fetchCarA = parkingLot.fetch(ticketA);
        Car fetchCarB = parkingLot.fetch(ticketB);
        //then
        assertEquals(carA,fetchCarA);
        assertEquals(carB,fetchCarB);
    }


    @Test
    @Disabled
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
    @Disabled
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
