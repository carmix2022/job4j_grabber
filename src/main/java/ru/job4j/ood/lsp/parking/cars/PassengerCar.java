package ru.job4j.ood.lsp.parking.cars;

import ru.job4j.ood.lsp.parking.ParkingService;

public class PassengerCar extends AbstractCar {
    private static final int CAR_SIZE = 1;
    @Override
    public int getSize() {
        return CAR_SIZE;
    }
}
