package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.isA;

/**
 * @Author weitangzhao
 **/
public class ParkingManagerTest {
    @Test
    @DisplayName("story6-AC1 The parking lot service manager can add parking boys to management list." )
    void should_validate_management_length__when_add_give_parking_boys() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        ArrayList<ParkingBoy> boys = new ArrayList<>();
        boys.add(new ParkingBoy());
        boys.add(new ParkingBoy());
        boys.add(new ParkingBoy());
        //when
        parkingManager.addParkingBoys(boys);
        parkingManager.addParkingBoy(new ParkingBoy());
        //then
        assertEquals(4,parkingManager.getManagement().size());
    }

    @Test
    @DisplayName("story6-AC1 The parking lot manager can specify a parking boy on the list to park or fetch the car (only from the parking lots managed by that parking boy).")
    void should_validate_parkingTicket_when_park_give_lot_and_car() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        ParkingLot mocParkingLot1 = mock(ParkingLot.class);
        ParkingBoy mockParkingBoy1 = mock(ParkingBoy.class);
        ParkingBoy mockParkingBoy2 = mock(ParkingBoy.class);
        parkingManager.addParkingBoy(mockParkingBoy1);
        parkingManager.addParkingBoy(mockParkingBoy2);
        ParkingTicket mockParkingTicket = mock(ParkingTicket.class);
        when(mockParkingBoy1.isManged(eq(mocParkingLot1))).thenReturn(false);
        when(mockParkingBoy2.isManged(eq(mocParkingLot1))).thenReturn(true);
        Car car = new Car();
        when(mockParkingBoy2.park(eq(car))).thenReturn(mockParkingTicket);
        //when
        ParkingTicket actuaParkingTicket = parkingManager.park(mocParkingLot1,car);
        //then
        assertEquals(mockParkingTicket,actuaParkingTicket);
    }

    @Test
    @DisplayName("story6-AC2 The parking lot service manager can also manage parking lots. " +
            "And (s)he can park or fetch the car just as a standard parking boy (Story 3). " +
            "Note that (s)he can only store and fetch the car from his/her own parking lots.")
    void should___when__give_() {
        //given
        ParkingLot parkingLot = mock(ParkingLot.class);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingManager parkingManager = new ParkingManager(parkingLots);
        ParkingTicket mockParkingTicket = mock(ParkingTicket.class);
        Car mockCar = mock(Car.class);
        //when
        ParkingTicket actualTicket = parkingManager.park(mockCar);
        Car actualCar = parkingManager.fetch(actualTicket);
        //then
        assertEquals(mockParkingTicket,actualTicket);
        assertEquals(mockCar,actualCar);
    }
}
