package ru.job4j.ood.lsp.parking.parkstore;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.ParkingService;
import ru.job4j.ood.lsp.parking.CarParkingServise;
import ru.job4j.ood.lsp.parking.cars.Car;
import ru.job4j.ood.lsp.parking.cars.PassengerCar;
import ru.job4j.ood.lsp.parking.cars.TruckCar;
import ru.job4j.ood.lsp.parking.controller.CarParkingController;
import ru.job4j.ood.lsp.parking.controller.ParkingController;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены")
class CarParkingServiceTest {

    @Test
    public void whenPassengerCarParksAndThereIsFreeSpaceThenOK() {
        ParkingStore parkingStore = new CarParkingStore(1, 1);
        ParkingController parkingController = new CarParkingController(parkingStore);
        ParkingService parkingService = new CarParkingServise(parkingController);
        Car passengerCar = new PassengerCar();
        passengerCar.park(parkingService);
        assertThat(passengerCar.getParkStatus()).isTrue();
    }

    @Test
    public void whenPassengerCarParksAndThereIsNotFreeSpaceThenFalse() {
        ParkingStore parkingStore = new CarParkingStore(1, 1);
        ParkingController parkingController = new CarParkingController(parkingStore);
        ParkingService parkingService = new CarParkingServise(parkingController);
        Car passengerCar1 = new PassengerCar();
        Car passengerCar2 = new PassengerCar();
        passengerCar1.park(parkingService);
        passengerCar2.park(parkingService);
        List<Boolean> expectedCarsParkStatuses = List.of(true, false);
        List<Boolean> factCarsParkStatuses = List.of(
                passengerCar1.getParkStatus(), passengerCar2.getParkStatus());
        assertThat(factCarsParkStatuses).isEqualTo(expectedCarsParkStatuses);
    }

    @Test
    public void whenTruckCarSize2ParksAndThereIsFreeSpaceThenOK() {
        ParkingStore parkingStore = new CarParkingStore(1, 1);
        ParkingController parkingController = new CarParkingController(parkingStore);
        ParkingService parkingService = new CarParkingServise(parkingController);
        Car truckCar = new TruckCar(2);
        truckCar.park(parkingService);
        assertThat(truckCar.getParkStatus()).isTrue();
    }

    @Test
    public void when2TruckCarSize2ParksAndThereIsFreeSpaceThenOK() {
        ParkingStore parkingStore = new CarParkingStore(2, 1);
        ParkingController parkingController = new CarParkingController(parkingStore);
        ParkingService parkingService = new CarParkingServise(parkingController);
        Car truckCar1 = new TruckCar(2);
        Car truckCar2 = new TruckCar(2);
        truckCar1.park(parkingService);
        truckCar2.park(parkingService);
        List<Boolean> factCarsParkStatuses = List.of(truckCar1.getParkStatus(), truckCar2.getParkStatus());
        assertThat(factCarsParkStatuses).doesNotContain(false);
    }

    @Test
    public void when2TruckCarsSize2ParkAndThereIsNoFreeSpaceThenError() {
        ParkingStore parkingStore = new CarParkingStore(1, 1);
        ParkingController parkingController = new CarParkingController(parkingStore);
        ParkingService parkingService = new CarParkingServise(parkingController);
        Car truckCar1 = new TruckCar(2);
        Car truckCar2 = new TruckCar(2);
        truckCar1.park(parkingService);
        truckCar2.park(parkingService);
        List<Boolean> expectedCarsParkStatuses = List.of(true, false);
        List<Boolean> factCarsParkStatuses = List.of(
                truckCar1.getParkStatus(), truckCar2.getParkStatus());
        assertThat(factCarsParkStatuses).isEqualTo(expectedCarsParkStatuses);
    }

    @Test
    public void whenTruckAndPassengerCarsParkAndThereIsFreeSpaceThenOK() {
        ParkingStore parkingStore = new CarParkingStore(3, 1);
        ParkingController parkingController = new CarParkingController(parkingStore);
        ParkingService parkingService = new CarParkingServise(parkingController);
        Car passengerCar = new PassengerCar();
        Car truckCar1 = new TruckCar(2);
        Car truckCar2 = new TruckCar(2);
        passengerCar.park(parkingService);
        truckCar1.park(parkingService);
        truckCar2.park(parkingService);
        List<Boolean> factCarsParkStatuses = List.of(
                passengerCar.getParkStatus(), truckCar1.getParkStatus(), truckCar2.getParkStatus());
        assertThat(factCarsParkStatuses).doesNotContain(false);
    }

    @Test
    public void whenTruckAndPassengerCarsParkAndThereIsNoFreeSpaceThenError() {
        ParkingStore parkingStore = new CarParkingStore(2, 1);
        ParkingController parkingController = new CarParkingController(parkingStore);
        ParkingService parkingService = new CarParkingServise(parkingController);
        Car passengerCar = new PassengerCar();
        Car truckCar1 = new TruckCar(2);
        Car truckCar2 = new TruckCar(2);
        passengerCar.park(parkingService);
        truckCar1.park(parkingService);
        truckCar2.park(parkingService);
        List<Boolean> expectedCarsParkStatuses = List.of(true, true, false);
        List<Boolean> factCarsParkStatuses = List.of(passengerCar.getParkStatus(),
                truckCar1.getParkStatus(), truckCar2.getParkStatus());
        assertThat(factCarsParkStatuses).isEqualTo(expectedCarsParkStatuses);
    }

    @Test
    public void whenTruckCarsPbarkAndThereIsNoFreeSpaceThenError() {
        ParkingStore parkingStore = new CarParkingStore(3, 0);
        ParkingController parkingController = new CarParkingController(parkingStore);
        ParkingService parkingService = new CarParkingServise(parkingController);
        Car truckCar1 = new TruckCar(2);
        Car truckCar2 = new TruckCar(2);
        truckCar1.park(parkingService);
        truckCar2.park(parkingService);
        List<Boolean> expectedCarsParkStatuses = List.of(true, false);
        List<Boolean> factCarsParkStatuses = List.of(truckCar1.getParkStatus(), truckCar2.getParkStatus());
        assertThat(factCarsParkStatuses).isEqualTo(expectedCarsParkStatuses);
    }

}