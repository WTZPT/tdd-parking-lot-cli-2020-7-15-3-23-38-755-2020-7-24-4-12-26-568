package com.oocl.cultivation;

import java.util.HashMap;

public class ParkingLot {
    private HashMap<ParkingTicket, Car> spots = new HashMap<>();
    private int capacity = 10;

    public ParkingTicket park(Car car) {
        if(spots.size() + 1 > capacity){
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket(false, car.getLicenseNumber());
        spots.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(parkingTicket == null || parkingTicket.getUsed()) {
            return null;
        }
        return this.spots.get(parkingTicket);
    }

    public HashMap<ParkingTicket, Car> getSpots() {
        return spots;
    }

    public void setSpots(HashMap<ParkingTicket, Car> spots) {
        this.spots = spots;
    }
}
