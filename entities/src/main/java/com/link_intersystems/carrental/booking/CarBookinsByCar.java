package com.link_intersystems.carrental.booking;

import com.link_intersystems.carrental.CarId;

import java.util.*;

public class CarBookinsByCar extends AbstractMap<CarId, CarBooking> {

    private Map<CarId, CarBooking> bookingsByCar = new HashMap<>();

    public CarBookinsByCar(List<CarBooking> carBookings) {
        for (CarBooking carBooking : carBookings) {
            CarId carId = carBooking.getCarId();
            bookingsByCar.put(carId, carBooking);
        }
    }

    @Override
    public Set<Entry<CarId, CarBooking>> entrySet() {
        return Collections.unmodifiableMap(bookingsByCar).entrySet();
    }

    public boolean contains(CarId carId) {
        return keySet().contains(carId);
    }
}