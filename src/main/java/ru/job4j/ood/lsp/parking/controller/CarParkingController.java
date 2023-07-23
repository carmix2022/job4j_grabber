package ru.job4j.ood.lsp.parking.controller;

import ru.job4j.ood.lsp.parking.cars.Car;
import ru.job4j.ood.lsp.parking.parkstore.ParkingStore;

import java.util.List;

public class CarParkingController implements ParkingController {
    private ParkingStore parkingStore;
    private int truckCarsParkingSpaceOccupancy;
    private int passengerCarsParkingSpaceOccupancy;

    public CarParkingController(ParkingStore parkingStore) {
        this.parkingStore = parkingStore;
    }

    @Override
    public boolean checkAndPark(Car car) {
        int carSize = car.getSize();
        int truckCarsParkingCapacity = parkingStore.getTruckCarsParkingCapacity();
        int passengerCarsParkingCapacity = parkingStore.getPassengerCarsParkingCapacity();
        if (carSize > 1 && (truckCarsParkingSpaceOccupancy < truckCarsParkingCapacity)) {
            parkingStore.addCarToTruckCarsParking(car);
            truckCarsParkingSpaceOccupancy++;
            return true;
        }
        if (carSize > 1 && (truckCarsParkingSpaceOccupancy == truckCarsParkingCapacity)
                && passengerCarsParkingSpaceOccupancy <= passengerCarsParkingCapacity - carSize) {
            parkingStore.addCarToPassengerCarsParking(car);
            passengerCarsParkingSpaceOccupancy += carSize;
            return true;
        }
        if (carSize == 1 && (passengerCarsParkingSpaceOccupancy < passengerCarsParkingCapacity)) {
            parkingStore.addCarToPassengerCarsParking(car);
            passengerCarsParkingSpaceOccupancy++;
            return true;
        }
        return false;
    }
}
