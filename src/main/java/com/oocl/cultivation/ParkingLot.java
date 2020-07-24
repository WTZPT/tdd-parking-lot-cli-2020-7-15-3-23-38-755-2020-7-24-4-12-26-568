package com.oocl.cultivation;

import java.util.HashMap;

public class ParkingLot {
    HashMap<ParkingTicket,Car> spots = new HashMap<>();


    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket(false,car.getLicenseNumber());
        spots.put(parkingTicket,car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return  this.spots.get(parkingTicket);
    }
}
