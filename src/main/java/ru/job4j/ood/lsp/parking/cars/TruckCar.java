package ru.job4j.ood.lsp.parking.cars;

import ru.job4j.ood.lsp.parking.parking.Parking;

public class TruckCar implements Car {
    private final int carSize;

    public TruckCar(int carSize) {
        this.carSize = carSize;
    }

    private boolean isParked = false;

    @Override
    public boolean getParkStatus() {
        return isParked;
    }

    @Override
    public void setParkStatus(boolean bool) {
        isParked = bool;
    }

    @Override
    public boolean park(Parking parking) {
        return false;
    }

    @Override
    public boolean unpark(Parking parking) {
        return false;
    }
}
