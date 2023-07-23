package ru.job4j.ood.lsp.parking.parkstore;

import ru.job4j.ood.lsp.parking.cars.Car;
import ru.job4j.ood.lsp.parking.controller.ParkingController;

import java.util.ArrayList;
import java.util.List;

public class CarParkingStore implements ParkingStore {
    private final List<Car> passengerCarParking = new ArrayList<>();
    private final List<Car> truckCarParking = new ArrayList<>();
    private final int passengerCarsParkingSpacesCount;
    private final int truckCarsParkingSpacesCount;

    public CarParkingStore(int passengerCarsParkingSpacesCount, int truckCarsParkingSpacesCount) {
        this.passengerCarsParkingSpacesCount = passengerCarsParkingSpacesCount;
        this.truckCarsParkingSpacesCount = truckCarsParkingSpacesCount;
    }

    @Override
    public void addCarToPassengerCarsParking(Car car) {
        passengerCarParking.add(car);
    }
    @Override
    public void addCarToTruckCarsParking(Car car) {
        truckCarParking.add(car);
    }
    @Override
    public int getPassengerCarsParkingCapacity() {
        return passengerCarsParkingSpacesCount;
    }
    @Override
    public int getTruckCarsParkingCapacity() {
        return truckCarsParkingSpacesCount;
    }
}
