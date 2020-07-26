package com.oocl.cultivation;

import java.util.ArrayList;

/**
 * @Author weitangzhao
 **/
public class ParkingManager {
    private ArrayList<ParkingBoy> management = new ArrayList<>();
    private ParkingLot parkingLot;
    private String errorMessage;

    public ParkingManager() {
    }

    public ParkingManager(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void addParkingBoys(ArrayList<ParkingBoy> boys) {
        management.addAll(boys);
    }

    public ArrayList<ParkingBoy> getManagement() {
        return management;
    }

    public void setManagement(ArrayList<ParkingBoy> management) {
        this.management = management;
    }

    public ParkingTicket park(ParkingLot parkingLot, Car car) {
        ParkingBoy pb = null;
        for (ParkingBoy parkingBoy : this.management) {
            if (parkingBoy.isManged(parkingLot)) {
                pb = parkingBoy;
                break;
            }
        }

        return pb.park(car);
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        this.management.add(parkingBoy);
    }

    public ParkingTicket park(Car car) {
        if (this.parkingLot.isLotFull()) {
            this.errorMessage = ParkingLot.NOT_POSIOTION;
            return null;
        }
        return parkingLot.park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null || parkingTicket.equals(null)) {
            this.errorMessage = ParkingLot.NULL_TICKET;
            return null;
        }
        if(parkingTicket.getUsed()){
            this.errorMessage = ParkingLot.HAS_USED;
            return null;
        }

        return this.parkingLot.fetch(parkingTicket);
    }

    public String query() {
        return this.errorMessage;
    }
}
