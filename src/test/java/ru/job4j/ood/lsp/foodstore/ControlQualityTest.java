package ru.job4j.ood.lsp.foodstore;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstore.food.Bread;
import ru.job4j.ood.lsp.foodstore.food.Cheese;
import ru.job4j.ood.lsp.foodstore.food.Food;
import ru.job4j.ood.lsp.foodstore.food.Milk;
import ru.job4j.ood.lsp.foodstore.store.Shop;
import ru.job4j.ood.lsp.foodstore.store.Store;
import ru.job4j.ood.lsp.foodstore.store.Trash;
import ru.job4j.ood.lsp.foodstore.store.Warehouse;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenControlQualityThenOK() {
        List<Store> stores = List.of(
                Trash.getInstance(),
                Warehouse.getInstance(),
                Shop.getInstance()
        );
        stores.forEach(Store::clear);
        Food f1 = new Milk("Prostokvashino", LocalDate.of(2023, 9, 1),
                LocalDate.of(2023, 7, 19), 20.5, 10);
        Food f2 = new Bread("Borodinskiy", LocalDate.of(2023, 8, 1),
                LocalDate.of(2023, 7, 10), 40.5, 15);
        Food f3 = new Cheese("Poshekhonskiy", LocalDate.of(2023, 7, 25),
                LocalDate.of(2023, 7, 7), 30.5, 10);
        Food f4 = new Cheese("Kosichka", LocalDate.of(2023, 6, 1),
                LocalDate.of(2023, 1, 1), 30.5, 5);
        List<Food> list = List.of(f1, f2, f3, f4);
        ControlQuality cq = new ControlQuality(stores);
        cq.controlQuality(list, LocalDate.of(2023, 7, 22));
        List<String> expected = List.of("[Food{name='Kosichka', expiryDate=2023-06-01, createDate=2023-01-01, price=30.5, discount=5.0}]",
                "[Food{name='Prostokvashino', expiryDate=2023-09-01, createDate=2023-07-19, price=20.5, discount=10.0}]",
                "[Food{name='Borodinskiy', expiryDate=2023-08-01, createDate=2023-07-10, price=40.5, discount=15.0}, "
                        + "Food{name='Poshekhonskiy', expiryDate=2023-07-25, createDate=2023-07-07, price=27.45, discount=10.0}]");
        List<String> fact = List.of(
                Trash.getInstance().getList().toString(),
                Warehouse.getInstance().getList().toString(),
                Shop.getInstance().getList().toString()
        );
        assertThat(fact).isEqualTo(expected);
    }

    @Test
    public void whenResortAndAllExpiredThenAllInTrash() {
        List<Store> stores = List.of(
                Trash.getInstance(),
                Warehouse.getInstance(),
                Shop.getInstance()
        );
        stores.forEach(Store::clear);
        Food f1 = new Milk("Prostokvashino", LocalDate.of(2023, 9, 1),
                LocalDate.of(2023, 7, 19), 20.5, 10);
        Food f2 = new Bread("Borodinskiy", LocalDate.of(2023, 8, 1),
                LocalDate.of(2023, 7, 10), 40.5, 15);
        Food f3 = new Cheese("Poshekhonskiy", LocalDate.of(2023, 7, 25),
                LocalDate.of(2023, 7, 7), 30.5, 10);
        Food f4 = new Cheese("Kosichka", LocalDate.of(2023, 6, 1),
                LocalDate.of(2023, 1, 1), 30.5, 5);
        List<Food> list = List.of(f1, f2, f3, f4);
        ControlQuality cq = new ControlQuality(stores);
        cq.controlQuality(list, LocalDate.of(2023, 7, 22));
        cq.resort(LocalDate.of(2023, 12, 22));
        List<String> expected = List.of("[Food{name='Kosichka', expiryDate=2023-06-01, createDate=2023-01-01, price=30.5, discount=5.0}, "
                        + "Food{name='Prostokvashino', expiryDate=2023-09-01, createDate=2023-07-19, price=20.5, discount=10.0}, "
                        + "Food{name='Borodinskiy', expiryDate=2023-08-01, createDate=2023-07-10, price=40.5, discount=15.0}, "
                        + "Food{name='Poshekhonskiy', expiryDate=2023-07-25, createDate=2023-07-07, price=27.45, discount=10.0}]",
                "[]", "[]");
        List<String> fact = List.of(
                Trash.getInstance().getList().toString(),
                Warehouse.getInstance().getList().toString(),
                Shop.getInstance().getList().toString()
        );
        assertThat(fact).isEqualTo(expected);
    }
}