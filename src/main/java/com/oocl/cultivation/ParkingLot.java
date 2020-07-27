package com.oocl.cultivation;

import java.util.HashMap;

public class ParkingLot {

    private HashMap<ParkingTicket, Car> spots = new HashMap<>();
    private int capacity = 10;

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket(false);
        spots.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car car = this.spots.get(parkingTicket);
        this.spots.remove(parkingTicket);
        parkingTicket.setUsed(true);
        return car;
    }

    public HashMap<ParkingTicket, Car> getSpots() {
        return spots;
    }

    public boolean isLotFull() {
        return capacity < (spots.size() + 1);
    }

    public int hasSurplus() {
        return this.capacity - this.spots.size();
    }

    public double getPositionRate() {
        return hasSurplus() / (this.capacity * 1.0);
    }

}
