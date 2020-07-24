package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.text.MessageFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class ParkingBoyTest {

    ParkingLot mockParkingLot;

    @BeforeEach
    void setUp(){
        mockParkingLot = mock(ParkingLot.class);
    }

    @Test
    @DisplayName("story1-ac1-The parking boy can park a car into the parking lot and returns a parking ticket.")
    void should_return_ticket_when_park_given_car() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        //when
        ParkingTicket ticket = parkingBoy.park(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    @DisplayName("story1-ac1-The customer can give the parking ticket back to the parking boy to fetch the car.")
    void should_return_car_when_fetch_given_parking_ticket() {
        //given
        when(mockParkingLot.fetch(isA(ParkingTicket.class))).thenReturn(new Car());
        ParkingBoy parkingBoy = new ParkingBoy(mockParkingLot);
        //when
        Car car = parkingBoy.fetch(new ParkingTicket());
        //then
        assertNotNull(car);
    }

    @Test
    @DisplayName("story1-ac2-The parking boy can park multiple cars into on parking lot")
    void should_verify_10_when_park_given_10_cars() {
        //given
        when(mockParkingLot.park(isA(Car.class))).thenReturn(null);
        ArrayList<Car> carArrayList = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(mockParkingLot);
        for (int i = 0; i < 10; i++) {
            carArrayList.add(new Car(MessageFormat.format("C{0}",i)));
        }
        //when
        for (Car car : carArrayList) {
            parkingBoy.park(car);
        }
        //then
        verify(mockParkingLot,times(10)).park(isA(Car.class));
    }

    @Test
    @DisplayName("story1-ac2-The parking boy can fetch right car using correspond ticket.")
    void should_validate_car_ticket_when_fetch_given_two_cars() {
        //given
        when(mockParkingLot.fetch(isA(ParkingTicket.class))).thenReturn(new Car());
        //when
        ParkingBoy parkingBoy = new ParkingBoy(mockParkingLot);
        parkingBoy.fetch(new ParkingTicket());
        parkingBoy.fetch(new ParkingTicket());
        //then
        verify(mockParkingLot,times(2)).fetch(isA(ParkingTicket.class));
    }

    @Test
    @DisplayName("")
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
