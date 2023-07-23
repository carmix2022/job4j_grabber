package ru.job4j.ood.lsp.parking.cars;

import ru.job4j.ood.lsp.parking.ParkingService;

public abstract class AbstractCar implements Car {
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
}
