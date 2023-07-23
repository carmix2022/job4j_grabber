package ru.job4j.ood.lsp.parking.cars;

import ru.job4j.ood.lsp.parking.ParkingService;

public interface Car {

    boolean getParkStatus();
    boolean park(ParkingService parkingService);
    int getSize();
}
