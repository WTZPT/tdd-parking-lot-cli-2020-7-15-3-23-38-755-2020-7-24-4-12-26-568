package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Comparator;

public class ParkingSmartBoy extends ParkingBoy {

    public ParkingSmartBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public ParkingSmartBoy(ArrayList<ParkingLot> lots) {
        super(lots);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = parkingLots.stream().max(Comparator.comparing(ParkingLot::hasSurplus)).get();
        if (parkingLot.isLotFull()) {
            this.errorMsg = "Not enough position.";
            return null;
        }
        ParkingTicket parkingTicket = parkingLot.park(car);
        return parkingTicket;
    }
}
