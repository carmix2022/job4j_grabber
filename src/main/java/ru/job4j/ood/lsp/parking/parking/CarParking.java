package ru.job4j.ood.lsp.parking.parking;

import ru.job4j.ood.lsp.parking.cars.Car;

import java.util.ArrayList;
import java.util.List;

public class CarParking implements Parking {

    private final Car[] passengerCarParking;
    private final Car[] truckCarParking;

    public CarParking(int passengerCarsParkingSpacesCount, int truckCarsParkingSpacesCount) {
        this.passengerCarParking = new Car[passengerCarsParkingSpacesCount];
        this.truckCarParking = new Car[truckCarsParkingSpacesCount];
    }

    @Override
    public void park(Car car) {
    }
}
