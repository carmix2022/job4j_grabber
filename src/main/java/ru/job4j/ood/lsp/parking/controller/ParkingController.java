package ru.job4j.ood.lsp.parking.controller;

import ru.job4j.ood.lsp.parking.cars.Car;

public interface ParkingController {
    boolean checkAndPark(Car car);
}
