package ru.job4j.ood.lsp.parking.cars;

import ru.job4j.ood.lsp.parking.controller.ParkingController;
import ru.job4j.ood.lsp.parking.parking.Parking;

public interface Car {

    boolean getParkStatus();
    void setParkStatus(boolean bool);
    boolean park(Parking parking);
    boolean unpark(Parking parking);
}
