package com.oocl.cultivation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {

    @Test
    @DisplayName("story1-AC2 The parking lot can accept park multiple cars ")
    void should_return_quantity_10_when_park_given_10_cars() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        List<Car> cars = new ArrayList<>();
        //todo two
        for (int i = 0; i < 10; i++) {
            cars.add(new Car());
        }
        //when
        for (Car car : cars) {
            parkingLot.park(car);
        }
        //then
        assertEquals(10, parkingLot.getSpots().size());
    }

    @Test
    @DisplayName("story1-AC2  Give the parking ticket back to the parking lot to fetch the car.")
    void should_validate_car_ticket_when_fetch_given_two_cars() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car carA = new Car();
        Car carB = new Car();
        ParkingTicket ticketA = parkingLot.park(carA);
        ParkingTicket ticketB = parkingLot.park(carB);
        //when
        Car actualFetchCarA = parkingLot.fetch(ticketA);
        Car actualFetchCarB = parkingLot.fetch(ticketB);
        //then
        assertEquals(carA, actualFetchCarA);
        assertEquals(carB, actualFetchCarB);
    }


    @Test
    @Disabled
    @DisplayName("story1-AC3 If gives a wrong ticket (the parking boy does not provide the ticket) when fetching. Then no car should be fetched.")
    void should_return_null_when_fetch_given_wrong_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = new ParkingTicket();
        //when
        Car car = parkingLot.fetch(parkingTicket);
        //then
        assertEquals(null, car);
    }

    @Test
    @Disabled
    @DisplayName("story1-AC3 If does not give a ticket when fetching. Then no car should be fetched.")
    void should_return_null_when_fetch_given_null() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        //when
        Car car = parkingLot.fetch(null);
        //then
        assertEquals(null, car);
    }

    @Test
    @Disabled
    @DisplayName("story1-AC4 If give a ticket that has already been used, then fetch car unsuccessfully.")
    void should_return_null_when_fetch_given_used_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingTicket parkingTicket = parkingLot.park(new Car());
        //when
        parkingLot.fetch(parkingTicket);
        Car car = parkingLot.fetch(parkingTicket);
        //then
        assertEquals(null, car);
    }

    @Test
    @DisplayName("story1-AC5 The parking lot `s isLotFull method can get status about lot whether full")
    void should_validate_not_full_status__when_isLotFull() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        //when
        boolean actualStatus = parkingLot.isLotFull();
        //then
        assertEquals(false, actualStatus);
    }

    @Test
    @DisplayName("story1-AC5 The parking lot `s isLotFull method can get status about lot whether full" +
            "(the default capacity of a parking lot is 10)")
    void should_validate_full_status__when_isLotFull() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ArrayList<Car> carArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            carArrayList.add(new Car());
        }
        //when
        for (Car car : carArrayList) {
            parkingLot.park(car);
        }
        boolean actualStatus = parkingLot.isLotFull();
        //then
        assertEquals(true, actualStatus);
    }

    @Test
    @Disabled
    @DisplayName("story1-AC5 The parking lot has a capacity (the default capacity of a parking lot is 10). If there is no position, then the user cannot park the car into it. Thus (s)he will not get any ticket.")
    void should_return_null_when_park_given_No_11_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ArrayList<Car> carArrayList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            carArrayList.add(new Car());
        }
        //when
        ParkingTicket parkingTicket = new ParkingTicket();
        for (Car car : carArrayList) {
            parkingTicket = parkingLot.park(car);
        }
        //then
        assertEquals(null, parkingTicket);
    }

    @Test
    @DisplayName("story4-AC1 Get surplus of lot` car position")
    void should_return_4_when_hasSurplus_give_6_Cars() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 6; i++) {
            parkingLot.park(new Car());
        }
        //when
        int actualSurplus = parkingLot.hasSurplus();
        //then
        assertEquals(4, actualSurplus);
    }

    @Test
    @DisplayName("story5-AC1  Get positionRate of lot  ")
    void should_return_0_5_when_getPositionRate_give_5_cars() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 5; i++) {
            parkingLot.park(new Car());
        }
        //when
        Double actualPositionRate = parkingLot.getPositionRate();
        //then
        assertEquals(0.5,actualPositionRate);
    }
}
