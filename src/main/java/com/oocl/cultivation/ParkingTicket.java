package com.oocl.cultivation;

public class ParkingTicket {

    private boolean isUsed;

    public ParkingTicket() {
        this.isUsed = false;
    }


    public ParkingTicket(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public boolean getUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

}
