package com.link_intersystems.carrental.management.pickup.list;

import java.time.LocalDateTime;

public class PickupCarResponseModel {
    private int bookingNumber;
    private LocalDateTime pickupDate;
    private int odometer;

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public LocalDateTime getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDateTime pickupDate) {
        this.pickupDate = pickupDate;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }
}
