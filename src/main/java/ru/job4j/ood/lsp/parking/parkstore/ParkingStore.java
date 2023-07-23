package ru.job4j.ood.lsp.parking.parkstore;

import ru.job4j.ood.lsp.parking.cars.Car;
import ru.job4j.ood.lsp.parking.controller.ParkingController;

import java.util.List;

public interface ParkingStore {
    void addCarToPassengerCarsParking(Car car);
    void addCarToTruckCarsParking(Car car);
    int getPassengerCarsParkingCapacity();
    int getTruckCarsParkingCapacity();
}
