package com.oocl.cultivation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static com.oocl.cultivation.exception.ExceptionMessage.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @Author weitangzhao
 **/
public class ParkingManagerTest {
    @Test
    @DisplayName("story6-AC1 The parking lot service manager can add parking boys to management list.")
    void should_validate_management_length__when_add_give_parking_boys() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        ArrayList<ParkingBoy> boys = new ArrayList<>();
        boys.add(mock(ParkingBoy.class));
        boys.add(mock(ParkingBoy.class));
        boys.add(mock(ParkingBoy.class));
        //when
        parkingManager.addParkingBoys(boys);
        parkingManager.addParkingBoy(mock(ParkingBoy.class));
        //then
        assertEquals(4, parkingManager.getManagement().size());
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
        ParkingTicket actuaParkingTicket = parkingManager.park(mocParkingLot1, car);
        //then
        assertEquals(mockParkingTicket, actuaParkingTicket);
    }

    @Test
    @DisplayName("story6-AC2 The parking lot service manager can also manage parking lots. " +
            "And (s)he can park or fetch the car just as a standard parking boy (Story 3). " +
            "Note that (s)he can only store and fetch the car from his/her own parking lots.")
    void should_validate_ticket_and_car_when_park_and_fetch_give_car_and_ticket() {
        //given
        ParkingLot mockParkingLot = mock(ParkingLot.class);
        ParkingManager parkingManager = new ParkingManager(mockParkingLot);
        ParkingTicket mockParkingTicket = mock(ParkingTicket.class);
        Car mockCar = mock(Car.class);

        when(mockParkingLot.isLotFull()).thenReturn(false);
        when(mockParkingTicket.getUsed()).thenReturn(false);
        when(mockParkingLot.fetch(eq(mockParkingTicket))).thenReturn(mockCar);
        when(mockParkingLot.park(eq(mockCar))).thenReturn(mockParkingTicket);
        //when
        ParkingTicket actualTicket = parkingManager.park(mockCar);
        Car actualCar = parkingManager.fetch(actualTicket);
        //then
        assertEquals(mockParkingTicket, actualTicket);
        assertEquals(mockCar, actualCar);
    }

    @Test
    @DisplayName("story6-AC2 The parking lot service manager can provider error message that about NOT_POSIOTION by query")
    void should_validate_NOT_POSIOTION_error_message_when_park_not_position_give_car() {
        //given
        ParkingLot mockParkingLot = mock(ParkingLot.class);
        ParkingManager parkingManager = new ParkingManager(mockParkingLot);
        Car mockCar = mock(Car.class);

        when(mockParkingLot.isLotFull()).thenReturn(true);
        //when
        ParkingTicket actualTicket = parkingManager.park(mockCar);
        //then
        assertEquals(null, actualTicket);
        assertEquals(NOT_POSITION, parkingManager.query());
    }

    @Test
    @DisplayName("story6-AC2 The parking lot service manager can provider error message that about NULL_TICKET by query")
    void should_validate_NULL_TICKET_error_message_when_fetch_not_provide_ticket_give_null() {
        //given
        ParkingLot mockParkingLot = mock(ParkingLot.class);
        ParkingManager parkingManager = new ParkingManager(mockParkingLot);
        //when
        Car actualCar = parkingManager.fetch(null);
        String actualErrorMsg = parkingManager.query();
        //then
        assertEquals(null, actualCar);
        assertEquals( NULL_TICKET, actualErrorMsg);
    }

    @Test
    @DisplayName("story6-AC2 The parking lot service manager can provider error message that about HAS_USED by query")
    void should_validate_HAS_USED_when_fetch_give_ticket() {
        //given
        ParkingLot mockParkingLot = mock(ParkingLot.class);
        ParkingManager parkingManager = new ParkingManager(mockParkingLot);
        ParkingTicket mockTicket = mock(ParkingTicket.class);
        when(mockTicket.getUsed()).thenReturn(true);
        //when
        Car actualCar = parkingManager.fetch(mockTicket);
        String actualErrorMsg = parkingManager.query();
        //then
        assertEquals(null, actualCar);
        assertEquals( HAS_USED, actualErrorMsg);
    }

    @Test
    @DisplayName(" If the manager tells the parking boy to park or fetch the car, " +
            "then the manager should be able to display the error message to the customer if the parking boy failed to do the operation.")
    void should_validate_NOT_POSITION_when_park_fail_then_query() {
        //given
        ParkingLot mockParkingLot = mock(ParkingLot.class);
        ParkingManager parkingManager = new ParkingManager(mockParkingLot);
        ParkingBoy mockParkingBoy = mock(ParkingBoy.class);
        Car mockCar = new Car();
        when(mockParkingBoy.isManged(eq(mockParkingLot))).thenReturn(true);
        when(mockParkingBoy.park(eq(mockCar))).thenReturn(null);
        parkingManager.addParkingBoy(mockParkingBoy);
        //when
        when(mockParkingBoy.query()).thenReturn( NOT_POSITION);
        parkingManager.park(mockParkingLot, mockCar);
        String actualNotPositionErrorMsg = parkingManager.query();
        //then
        assertEquals(actualNotPositionErrorMsg,  NOT_POSITION);
    }

    @Test
    @DisplayName(" If the manager tells the parking boy to park or fetch the car, " +
            "then the manager should be able to display the error message to the customer if the parking boy failed to do the operation.")
    void should_validate_HAS_USED_and_NOT_PRODIVED_when_fetch_fail_then_quert() {
        //given
        ParkingLot mockParkingLot = mock(ParkingLot.class);
        ParkingManager parkingManager = new ParkingManager(mockParkingLot);
        ParkingBoy mockParkingBoy = mock(ParkingBoy.class);
        ParkingTicket mockTicket = mock(ParkingTicket.class);
        when(mockParkingBoy.isManged(eq(mockParkingLot))).thenReturn(true);
        parkingManager.addParkingBoy(mockParkingBoy);
        //when
        when(mockParkingBoy.fetch(eq(null))).thenReturn(null);
        when(mockParkingBoy.query()).thenReturn( NULL_TICKET);
        parkingManager.fetch(mockParkingLot, null);
        String actualNotProvideTicketErrorMsg = parkingManager.query();
        when(mockParkingBoy.fetch(eq(mockTicket))).thenReturn(null);
        when(mockParkingBoy.query()).thenReturn( HAS_USED);
        parkingManager.fetch(mockParkingLot,mockTicket);
        String actualHasUsedTicketErrorMsg = parkingManager.query();
        //then
        assertEquals( NULL_TICKET, actualNotProvideTicketErrorMsg);
        assertEquals( HAS_USED, actualHasUsedTicketErrorMsg);
    }
}
