package ru.job4j.ood.lsp.parking.cars;

import ru.job4j.ood.lsp.parking.ParkingService;

public class TruckCar extends AbstractCar {
    private final int carSize;

    public TruckCar(int carSize) {
        this.carSize = carSize;
    }

    @Override
    public int getSize() {
        return carSize;
    }
}
