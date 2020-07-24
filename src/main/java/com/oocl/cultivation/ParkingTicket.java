package com.oocl.cultivation;

public class ParkingTicket {

   private Boolean isUsed;
   private String licenseNumber;

    public ParkingTicket() {
    }

    public ParkingTicket(Boolean isUsed, String licenseNumber) {
        this.isUsed = isUsed;
        this.licenseNumber = licenseNumber;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
