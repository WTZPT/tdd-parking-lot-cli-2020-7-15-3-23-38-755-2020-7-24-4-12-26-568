package com.oocl.cultivation;

public class ParkingTicket {

   private boolean isUsed;
   private String licenseNumber;

    public ParkingTicket() {
    }

    public ParkingTicket(Boolean isUsed, String licenseNumber) {
        this.isUsed = isUsed;
        this.licenseNumber = licenseNumber;
    }

    public boolean getUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
