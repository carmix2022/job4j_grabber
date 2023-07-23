package ru.job4j.ood.lsp.parking;

import ru.job4j.ood.lsp.parking.cars.Car;
import ru.job4j.ood.lsp.parking.controller.ParkingController;

public class CarParkingServise implements ParkingService {
    private ParkingController parkingController;

    public CarParkingServise(ParkingController parkingController) {
        this.parkingController = parkingController;
    }

    @Override
    public boolean checkAndPark(Car car) {
        return parkingController.checkAndPark(car);
    }
}
