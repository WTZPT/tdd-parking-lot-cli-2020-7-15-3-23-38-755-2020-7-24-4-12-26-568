package com.oocl.cultivation;

import java.util.Objects;

public class Car {
    private String licenseNumber;

    public Car() {
    }

    public Car(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }


    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
