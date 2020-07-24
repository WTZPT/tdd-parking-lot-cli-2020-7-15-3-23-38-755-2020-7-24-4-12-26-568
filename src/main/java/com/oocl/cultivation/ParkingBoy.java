package com.oocl.cultivation;

import java.util.ArrayList;

public class ParkingBoy {

    private ArrayList<ParkingLot> parkingLots;
    private String errorMsg;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots = new ArrayList<>();
        this.parkingLots.add(parkingLot);
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = null;
        for ( ParkingLot lot : parkingLots) {
            parkingLot = lot;
            if(!lot.isLotFull()) {
                break;
            }
        }
        if(parkingLot.isLotFull()){
            this.errorMsg = "Not enough position.";
            return null;
        }
        ParkingTicket parkingTicket = parkingLot.park(car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if(parkingTicket == null) {
            this.errorMsg = "Please provide your parking ticket.";
            return null;
        }
        if(parkingTicket.getUsed()) {
            this.errorMsg = "Unrecognized parking ticket.";
            return null;
        }
        Car car = null;
        for (ParkingLot lot : parkingLots) {
            car = lot.fetch(parkingTicket);
            if(car != null) {
                break;
            }
        }
        return car;
    }

    public String query(){
        return this.errorMsg;
    }
}
