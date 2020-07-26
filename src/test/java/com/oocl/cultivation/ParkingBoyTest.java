package com.oocl.cultivation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class ParkingBoyTest {

    ParkingLot mockParkingLot;

    @BeforeEach
    void setUp() {
        mockParkingLot = mock(ParkingLot.class);
    }

    @Test
    @DisplayName("story1-AC1 The parking boy can park a car into the parking lot and returns a parking ticket.")
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
    @DisplayName("story1-AC1 The customer can give the parking ticket back to the parking boy to fetch the car.")
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
    @DisplayName("story1-AC2 The parking boy can park multiple cars into on parking lot")
    void should_verify_10_when_park_given_10_cars() {
        //given
        when(mockParkingLot.park(isA(Car.class))).thenReturn(null);
        ArrayList<Car> carArrayList = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(mockParkingLot);
        for (int i = 0; i < 10; i++) {
            carArrayList.add(new Car());
        }
        //when
        for (Car car : carArrayList) {
            parkingBoy.park(car);
        }
        //then
        verify(mockParkingLot, times(10)).park(isA(Car.class));
    }

    @Test
    @DisplayName("story1-AC2 The parking boy can fetch right car using correspond ticket.")
    void should_validate_car_ticket_when_fetch_given_two_cars() {
        //given
        when(mockParkingLot.fetch(isA(ParkingTicket.class))).thenReturn(new Car());
        //when
        ParkingBoy parkingBoy = new ParkingBoy(mockParkingLot);
        parkingBoy.fetch(new ParkingTicket());
        parkingBoy.fetch(new ParkingTicket());
        //then
        verify(mockParkingLot, times(2)).fetch(isA(ParkingTicket.class));
    }

    @Test
    @DisplayName("story1-AC3 If the customer gives a wrong ticket (the parking boy does not provide the ticket). Then no car should be fetched.")
    void should_return_null_when_fetch_given_wrong_tick() {
        //given
        when(mockParkingLot.fetch(isA(ParkingTicket.class))).thenReturn(null);
        ParkingBoy parkingBoy = new ParkingBoy(mockParkingLot);
        ParkingTicket parkingTicket = new ParkingTicket();
        //when
        Car car = parkingBoy.fetch(parkingTicket);
        //then
        assertEquals(null, car);
    }

    @Test
    @DisplayName("story1-AC3 If the customer does not give a ticket. Then no car should be fetched.")
    void should_return_null_when_fetch_given_null() {
        //given
        when(mockParkingLot.fetch(isA(ParkingTicket.class))).thenReturn(null);
        ParkingBoy parkingBoy = new ParkingBoy(mockParkingLot);
        //when
        Car car = parkingBoy.fetch(null);
        //then
        assertEquals(null, car);
    }

    @Test
    @DisplayName("story1-AC4 If the customer gives a ticket that has already been used. Then no car should be fetched.")
    void should_return_null_when_fetch_given_used_ticket() {
        //given
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setUsed(true);
        when(mockParkingLot.fetch(eq(parkingTicket))).thenReturn(null);
        ParkingBoy parkingBoy = new ParkingBoy(mockParkingLot);
        //when
        Car car = parkingBoy.fetch(parkingTicket);
        //then
        assertEquals(null, car);
    }

    @Test
    @DisplayName("story1-AC5 The parking lot has a capacity (the default capacity of a parking lot is 10). If there is no position, then the user cannot park the car into it. Thus (s)he will not get any ticket.")
    void should_return_null_when_park_given_new_car() {
        //given
        when(mockParkingLot.park(isA(Car.class))).thenReturn(null);
        ParkingBoy parkingBoy = new ParkingBoy(mockParkingLot);
        //when
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        //then
        assertEquals(null, parkingTicket);
    }

    @Test
    @DisplayName("story2-AC1 When the customer gives a wrong ticket (the parking boy does not provide the ticket / the ticket has been used). Then no car should be fetched. \n" +
            "If I query the error message, I can get an \"Unrecognized parking ticket.\".")
    void should_return_message_when_query_given_wrong_ticket() {
        //given
        when(mockParkingLot.fetch(isA(ParkingTicket.class))).thenReturn(null);
        ParkingBoy parkingBoy = new ParkingBoy(mockParkingLot);
        ParkingTicket parkingTicketUsed = new ParkingTicket();
        parkingTicketUsed.setUsed(true);
        //when
        Car carFromTicketUsed = parkingBoy.fetch(parkingTicketUsed);
        String ticketUsedErrorMsg = parkingBoy.query();
        //then
        assertEquals(null, carFromTicketUsed);
        assertEquals("Unrecognized parking ticket.", ticketUsedErrorMsg);
    }

    @Test
    @DisplayName("story2-AC2 When the customer does not provide a ticket when fetching a  car. The error message should be \"Please provide your parking ticket.\"")
    void should_return_message_when_query_given_null() {
        //given
        when(mockParkingLot.fetch(isA(ParkingTicket.class))).thenReturn(null);
        ParkingBoy parkingBoy = new ParkingBoy(mockParkingLot);
        //when
        Car car = parkingBoy.fetch(null);
        String errorMsg = parkingBoy.query();
        //then
        assertEquals(null, car);
        assertEquals("Please provide your parking ticket.", errorMsg);
    }

    @Test
    @DisplayName("story2-AC3 When the parking boy attempt to park a car into a parking lot without a position. The error message should be \"Not enough position.\"")
    void should_return_message_when_not_park_given_car() {
        //given
        when(mockParkingLot.isLotFull()).thenReturn(true);
        ParkingBoy parkingBoy = new ParkingBoy(mockParkingLot);

        //when
        ParkingTicket parkingTicket = parkingBoy.park(new Car());
        String errorMsg = parkingBoy.query();
        //then
        assertEquals(null, parkingTicket);
        assertEquals("Not enough position.", errorMsg);
    }

    @Test
    @Disabled
    @DisplayName("story4-ac1 The smart parking boy will always park cars to the parking lot which contains more empty positions.")
    void should_validate_lot__when_park_give_lots() {
        //given
        ArrayList<ParkingLot> lots = new ArrayList<>();
        ParkingLot lotAHas3Car = mock(ParkingLot.class);
        when(lotAHas3Car.hasSurplus()).thenReturn(7);
        when(lotAHas3Car.park(isA(Car.class))).thenReturn(null);
        ParkingLot lotBHas1Car = mock(ParkingLot.class);
        when(lotBHas1Car.hasSurplus()).thenReturn(9);
        when(lotBHas1Car.park(isA(Car.class))).thenReturn(null);
        ParkingLot lotCHas2Car = mock(ParkingLot.class);
        when(lotCHas2Car.hasSurplus()).thenReturn(8);
        when(lotCHas2Car.park(isA(Car.class))).thenReturn(null);
        lots.add(lotAHas3Car);
        lots.add(lotBHas1Car);
        lots.add(lotCHas2Car);
        Car car = new Car();
        //when
        ParkingBoy parkingBoy = new ParkingBoy(lots);
        parkingBoy.park(car);
        //then
        verify(lotBHas1Car, times(1)).park(eq(car));
    }

    @Test
    @DisplayName(" The super smart parking boy will always park cars to the parking lot which has a larger available position rate (positions available / total capacity).")
    void should_validate_largest_poition_rate_lot__when_park_give_lots() {
        //given
        ArrayList<ParkingLot> lots = new ArrayList<>();
        ParkingLot lotAHas3Car = mock(ParkingLot.class);
        when(lotAHas3Car.getPositionRate()).thenReturn(0.7);
        when(lotAHas3Car.park(isA(Car.class))).thenReturn(null);
        ParkingLot lotBHas1Car = mock(ParkingLot.class);
        when(lotBHas1Car.getPositionRate()).thenReturn(0.9);
        when(lotBHas1Car.park(isA(Car.class))).thenReturn(null);
        ParkingLot lotCHas2Car = mock(ParkingLot.class);
        when(lotCHas2Car.getPositionRate()).thenReturn(0.8);
        when(lotCHas2Car.park(isA(Car.class))).thenReturn(null);
        lots.add(lotAHas3Car);
        lots.add(lotBHas1Car);
        lots.add(lotCHas2Car);
        Car car = new Car();
        //when
        ParkingBoy parkingBoy = new ParkingBoy(lots);
        parkingBoy.park(car);
        //then
        verify(lotBHas1Car, times(1)).park(eq(car));
    }

    @Test
    @DisplayName("story6 isManged method can  Determine if the boy is in charge of the parking lot ")
    void should___when__give_() {
        //given
        ArrayList<ParkingLot> lots = new ArrayList<>();
        ParkingLot mockParkingLotA = mock(ParkingLot.class);
        lots.add(mockParkingLotA);
        ParkingLot mockParkingLotB = mock(ParkingLot.class);
        lots.add(mockParkingLotB);
        ParkingLot mockParkingLotC = mock(ParkingLot.class);
        lots.add(mockParkingLotC);
        ParkingLot mockParkingLotD = mock(ParkingLot.class);
        //when
        ParkingBoy parkingBoy = new ParkingBoy(lots);
        //then
        assertEquals(true, parkingBoy.isManged(mockParkingLotA));
        assertEquals(true, parkingBoy.isManged(mockParkingLotB));
        assertEquals(true, parkingBoy.isManged(mockParkingLotC));
        assertEquals(false, parkingBoy.isManged(mockParkingLotD));
    }
}