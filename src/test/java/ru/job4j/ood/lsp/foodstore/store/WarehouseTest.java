package ru.job4j.ood.lsp.foodstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstore.ControlQuality;
import ru.job4j.ood.lsp.foodstore.food.Bread;
import ru.job4j.ood.lsp.foodstore.food.Cheese;
import ru.job4j.ood.lsp.foodstore.food.Food;
import ru.job4j.ood.lsp.foodstore.food.Milk;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {
    @Test
    public void whenWarehouseThenOK() {
        Warehouse.getInstance().clear();
        LocalDate localDate = LocalDate.of(2023, 7, 22);
        Food f1 = new Milk("Prostokvashino", LocalDate.of(2023, 9, 1),
                LocalDate.of(2023, 7, 19), 20.5, 10);
        Food f2 = new Bread("Borodinskiy", LocalDate.of(2023, 8, 1),
                LocalDate.of(2023, 7, 10), 40.5, 15);
        Food f3 = new Cheese("Poshekhonskiy", LocalDate.of(2023, 7, 25),
                LocalDate.of(2023, 7, 7), 30.5, 10);
        Food f4 = new Cheese("Kosichka", LocalDate.of(2023, 6, 1),
                LocalDate.of(2023, 1, 1), 30.5, 5);
        List<Food> list = List.of(f1, f2, f3, f4);
        list.forEach(f -> Warehouse.getInstance().checkAndAdd(f, localDate));
        List<Food> expected = List.of(f1);
        List<Food> fact = Warehouse.getInstance().getList();
        Warehouse.getInstance().clear();
        assertThat(fact).isEqualTo(expected);
    }
}