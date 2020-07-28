package com.oocl.cultivation;

import com.oocl.cultivation.exception.ExceptionMessage;

import java.util.Comparator;

public class ParkingSuperSmartBoy extends ParkingBoy {
    public ParkingSuperSmartBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public ParkingSuperSmartBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLot = parkingLots.stream().max(Comparator.comparing(ParkingLot::getPositionRate)).get();
        if (parkingLot.isLotFull()) {
            this.errorMsg = ExceptionMessage.NOT_POSITION;
            return null;
        }
        ParkingTicket parkingTicket = parkingLot.park(car);
        return parkingTicket;
    }
}
