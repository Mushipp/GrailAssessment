package com.participant.information.system.dto;

import java.io.Serializable;

public class Address implements Serializable {
    // person who is associated with the address


    private String addressLine1;
    private String addressLine2;
    private String aptUnitNumber;
    private String city;
    private String state;
    private int zipCode;

    public Address(String addressLine1, String addressLine2, String aptUnitNumber, String city, String state, int zipCode) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.aptUnitNumber = aptUnitNumber;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAptUnitNumber() {
        return aptUnitNumber;
    }

    public void setAptUnitNumber(String aptUnitNumber) {
        this.aptUnitNumber = aptUnitNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", aptUnitNumber='" + aptUnitNumber + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                '}';
    }
}