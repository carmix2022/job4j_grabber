package ru.job4j.ood.lsp.parking.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.cars.Car;
import ru.job4j.ood.lsp.parking.cars.PassengerCar;
import ru.job4j.ood.lsp.parking.cars.TruckCar;
import ru.job4j.ood.tdd.*;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены")
class CarParkingTest {

    @Test
    public void whenPassengerCarParksAndThereIsFreeSpaceThenOK() {
        Parking parking = new CarParking(1, 1);
        Car passengerCar = new PassengerCar();
        passengerCar.park(parking);
        assertThat(passengerCar.getParkStatus()).isTrue();
    }

    @Test
    public void whenPassengerCarParksAndThereIsNotFreeSpaceThenFalse() {
        Parking parking = new CarParking(1, 1);
        Car passengerCar1 = new PassengerCar();
        Car passengerCar2 = new PassengerCar();
        passengerCar1.park(parking);
        assertThatException().isThrownBy(() -> passengerCar2.park(parking)).withMessage("There is no free space");
    }

    @Test
    public void whenTruckCarSize2ParksAndThereIsFreeSpaceThenOK() {
        Parking parking = new CarParking(1, 1);
        Car truckCar = new TruckCar(2);
        truckCar.park(parking);
        assertThat(truckCar.getParkStatus()).isTrue();
    }

    @Test
    public void when2TruckCarSize2ParksAndThereIsFreeSpaceThenOK() {
        Parking parking = new CarParking(2, 1);
        Car truckCar1 = new TruckCar(2);
        Car truckCar2 = new TruckCar(2);
        truckCar1.park(parking);
        truckCar2.getParkStatus();
        List<Boolean> factCarsParkStatuses = List.of(truckCar1.getParkStatus(), truckCar2.getParkStatus());
        assertThat(factCarsParkStatuses).doesNotContain(false);
    }

    @Test
    public void when2TruckCarsSize2ParkAndThereIsNoFreeSpaceThenError() {
        Parking parking = new CarParking(1, 1);
        Car truckCar1 = new TruckCar(2);
        Car truckCar2 = new TruckCar(2);
        truckCar1.park(parking);
        assertThatException().isThrownBy(() -> truckCar2.park(parking))
                .withMessage("There is no free space for this truck car with size = 2");
    }

    @Test
    public void whenTruckAndPassengerCarsParkAndThereIsFreeSpaceThenOK() {
        Parking parking = new CarParking(3, 1);
        Car passengerCar = new PassengerCar();
        Car truckCar1 = new TruckCar(2);
        Car truckCar2 = new TruckCar(2);
        passengerCar.park(parking);
        truckCar1.park(parking);
        truckCar2.park(parking);
        List<Boolean> expectedCarsParkStatuses = List.of(true, true, true);
        List<Boolean> factCarsParkStatuses = List.of(
                passengerCar.getParkStatus(), truckCar1.getParkStatus(), truckCar2.getParkStatus());
        assertThat(factCarsParkStatuses).isEqualTo(expectedCarsParkStatuses);
    }

    @Test
    public void whenTruckAndPassengerCarsParkAndThereIsNoFreeSpaceThenError() {
        Parking parking = new CarParking(2, 1);
        Car passengerCar = new PassengerCar();
        Car truckCar1 = new TruckCar(2);
        Car truckCar2 = new TruckCar(2);
        passengerCar.park(parking);
        truckCar1.park(parking);
        assertThatException().isThrownBy(() -> truckCar2.park(parking))
                .withMessage("There is no free space for this truck car with size = 2");
    }

    @Test
    public void whenPassengerCarParksAndThereIsNotFreeSpaceThenError() {
        Parking parking = new CarParking(1, 1);
        Car passengerCar1 = new PassengerCar();
        Car passengerCar2 = new PassengerCar();
        passengerCar1.park(parking);
        assertThatException().isThrownBy(() -> passengerCar2.park(parking))
                .withMessage("There is no free space for this passenger car");
    }

    @Test
    public void whenPassengerCarsParkAndUnparkAndThereIsFreeSpaceThenOK() {
        Parking parking = new CarParking(1, 1);
        Car passengerCar1 = new PassengerCar();
        Car passengerCar2 = new PassengerCar();
        passengerCar1.park(parking);
        passengerCar1.unpark(parking);
        passengerCar2.park(parking);
        List<Boolean> factCarsParkStatuses = List.of(passengerCar1.getParkStatus(), passengerCar2.getParkStatus());
        assertThat(factCarsParkStatuses).containsExactly(false, true);
    }

    @Test
    public void whenPassengerAndTruckCarsParkAndUnparkAndThereIsFreeSpaceThenOK() {
        Parking parking = new CarParking(2, 1);
        Car passengerCar = new PassengerCar();
        Car truckCar1 = new TruckCar(2);
        Car truckCar2 = new TruckCar(2);
        passengerCar.park(parking);
        truckCar1.park(parking);
        passengerCar.unpark(parking);
        truckCar2.park(parking);
        List<Boolean> factCarsParkStatuses = List.of(passengerCar.getParkStatus(),
                truckCar1.getParkStatus(), truckCar2.getParkStatus());
        assertThat(factCarsParkStatuses).containsExactly(false, true, true);
    }

}