package com.oocl.cultivation;

import java.util.ArrayList;

import static com.oocl.cultivation.exception.ExceptionMessage.*;

public class ParkingBoy implements ParkingBehavior {

    protected ArrayList<ParkingLot> parkingLots;
    protected String errorMsg;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots = new ArrayList<>();
        this.parkingLots.add(parkingLot);
    }


    public ParkingBoy(ArrayList<ParkingLot> lots) {
        this.parkingLots = lots;
    }

    @Override
    public ParkingTicket park(Car car) {

        ParkingLot parkingLot = parkingLots.stream().filter(lot -> !lot.isLotFull()).findFirst().orElse(null);

        if (parkingLot == null) {
            this.errorMsg = NOT_POSITION;
            return null;
        }
        ParkingTicket parkingTicket = parkingLot.park(car);
        return parkingTicket;
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            this.errorMsg = NULL_TICKET;
            return null;
        }
        if (parkingTicket.getUsed()) {
            this.errorMsg = HAS_USED;
            return null;
        }
        Car car = null;
        for (ParkingLot lot : parkingLots) {
            car = lot.fetch(parkingTicket);
            if (car != null) {
                break;
            }
        }

        return car;
    }

    @Override
    public String query() {
        return this.errorMsg;
    }

    public boolean isManged(ParkingLot parkingLot) {
        return this.parkingLots.contains(parkingLot);
    }
}
