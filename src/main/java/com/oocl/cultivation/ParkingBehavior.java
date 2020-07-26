package com.oocl.cultivation;

public interface ParkingBehavior {

    ParkingTicket park(Car car);

    Car fetch(ParkingTicket parkingTicket);

    String query();
}
