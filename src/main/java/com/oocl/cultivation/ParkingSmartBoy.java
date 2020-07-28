package com.oocl.cultivation;

import com.oocl.cultivation.exception.ExceptionMessage;

import java.util.Comparator;

public class ParkingSmartBoy extends ParkingBoy {

    public ParkingSmartBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = parkingLots.stream().max(Comparator.comparing(ParkingLot::hasSurplus)).get();
        if (parkingLot.isLotFull()) {
            this.errorMsg = ExceptionMessage.NOT_POSITION;
            return null;
        }
        ParkingTicket parkingTicket = parkingLot.park(car);
        return parkingTicket;
    }
}
