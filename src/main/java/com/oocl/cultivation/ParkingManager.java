package com.oocl.cultivation;

import java.util.ArrayList;

/**
 * @Author weitangzhao
 **/
public class ParkingManager {
    ArrayList<ParkingBoy> management = new ArrayList<>();

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
        ParkingBoy pb = null ;
        for (ParkingBoy parkingBoy:this.management){
            if(parkingBoy.isManged(parkingLot)) {
                pb = parkingBoy;
                break;
            }
        }

        return  pb.park(car);
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        this.management.add(parkingBoy);
    }
}
