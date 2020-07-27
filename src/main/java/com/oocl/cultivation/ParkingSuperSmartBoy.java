package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Comparator;

public class ParkingSuperSmartBoy extends ParkingBoy {
    public ParkingSuperSmartBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public ParkingSuperSmartBoy(ArrayList<ParkingLot> lots) {
        super(lots);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = parkingLots.stream().max(Comparator.comparing(ParkingLot::getPositionRate)).get();
        if (parkingLot.isLotFull()) {
            this.errorMsg = "Not enough position.";
            return null;
        }
        ParkingTicket parkingTicket = parkingLot.park(car);
        return parkingTicket;
    }
}
