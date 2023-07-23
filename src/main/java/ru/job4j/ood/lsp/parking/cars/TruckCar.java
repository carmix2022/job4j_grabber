package ru.job4j.ood.lsp.parking.cars;

import ru.job4j.ood.lsp.parking.ParkingService;

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

    private void setParkStatus(boolean bool) {
        isParked = bool;
    }

    @Override
    public boolean park(ParkingService parkingService) {
        if (parkingService.checkAndPark(this)) {
            setParkStatus(true);
        }
        return getParkStatus();
    }

    @Override
    public int getSize() {
        return carSize;
    }
}
