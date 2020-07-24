package com.oocl.cultivation;

public class ParkingBoy {

    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = this.parkingLot.park(car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {

        return this.parkingLot.fetch(parkingTicket);
    }
}
