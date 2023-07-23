package ru.job4j.ood.lsp.parking.cars;

import ru.job4j.ood.lsp.parking.ParkingService;

public class PassengerCar implements Car {
    private static final int CAR_SIZE = 1;

    private boolean isParked = false;

    @Override
    public boolean getParkStatus() {
        return isParked;
    }
    public void setParkStatus(boolean bool) {
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
        return CAR_SIZE;
    }
}
