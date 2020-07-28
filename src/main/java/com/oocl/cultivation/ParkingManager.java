package com.oocl.cultivation;

import java.util.ArrayList;

import static com.oocl.cultivation.exception.ExceptionMessage.*;

/**
 * @Author weitangzhao
 **/
public class ParkingManager implements ParkingBehavior {
    private ArrayList<ParkingBoy> managements = new ArrayList<>();
    private ParkingLot parkingLot;
    private String errorMessage;

    public ParkingManager() {
    }

    public ParkingManager(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void addParkingBoys(ArrayList<ParkingBoy> boys) {
        managements.addAll(boys);
    }

    public ArrayList<ParkingBoy> getManagements() {
        return managements;
    }

    public ParkingTicket park(ParkingLot parkingLot, Car car) {
        ParkingBoy parkingBoy = getParkingBoy(parkingLot);

        ParkingTicket ticket = parkingBoy.park(car);
        if (ticket == null) {
            this.errorMessage = parkingBoy.query();
        }
        return ticket;
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        this.managements.add(parkingBoy);
    }

    @Override
    public ParkingTicket park(Car car) {
        if (this.parkingLot.isLotFull()) {
            this.errorMessage = NOT_POSITION;
            return null;
        }
        return parkingLot.park(car);
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            this.errorMessage = NULL_TICKET;
            return null;
        }
        if (parkingTicket.getUsed()) {
            this.errorMessage = HAS_USED;
            return null;
        }

        return this.parkingLot.fetch(parkingTicket);
    }

    @Override
    public String query() {
        return this.errorMessage;
    }

    public Car fetch(ParkingLot parkingLot, ParkingTicket parkingTicket) {
        ParkingBoy pb = getParkingBoy(parkingLot);

        Car car = pb.fetch(parkingTicket);

        if (car == null) {
            this.errorMessage = pb.query();
        }

        return car;
    }

    private ParkingBoy getParkingBoy(ParkingLot parkingLot) {

        return this.managements
                .stream()
                .filter(management -> management.isManged(parkingLot))
                .findFirst()
                .orElse(null);
    }
}
