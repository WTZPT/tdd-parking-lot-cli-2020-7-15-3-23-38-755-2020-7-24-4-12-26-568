package com.oocl.cultivation;

public class ParkingBoy {

    private ParkingLot parkingLot;
    private String errorMsg;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        if(this.parkingLot.isLotFull()){
            this.errorMsg = "Not enough position.";
            return null;
        }
        ParkingTicket parkingTicket = this.parkingLot.park(car);
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
        return this.parkingLot.fetch(parkingTicket);
    }

    public String query(){
        return this.errorMsg;
    }
}
