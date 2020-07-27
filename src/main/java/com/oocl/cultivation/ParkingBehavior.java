package com.oocl.cultivation;

public interface ParkingBehavior {

    public final static String NOT_POSITION = "Not enough position.";
    public final static String HAS_USED = "Unrecognized parking ticket.";
    public final static String NULL_TICKET = "Please provide your parking ticket.";


    ParkingTicket park(Car car);

    Car fetch(ParkingTicket parkingTicket);

    String query();
}
