package com.oocl.cultivation;

import java.util.HashMap;

public class ParkingLot {
    HashMap<ParkingTicket, Car> spots = new HashMap<>();


    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket(false, car.getLicenseNumber());
        spots.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return this.spots.get(parkingTicket);
    }

    public HashMap<ParkingTicket, Car> getSpots() {
        return spots;
    }

    public void setSpots(HashMap<ParkingTicket, Car> spots) {
        this.spots = spots;
    }
}
