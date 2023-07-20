package ru.job4j.ood.lsp.foodstore.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.foodstore.food.Bread;
import ru.job4j.ood.lsp.foodstore.food.Cheese;
import ru.job4j.ood.lsp.foodstore.food.Food;
import ru.job4j.ood.lsp.foodstore.food.Milk;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    @Test
    public void whenShopThenOK() {
        Shop.getInstance().clear();
        Food f1 = new Milk("Prostokvashino", LocalDate.of(2023, 9, 1),
                LocalDate.of(2023, 7, 19), 20.5, 10);
        Food f2 = new Bread("Borodinskiy", LocalDate.of(2023, 8, 1),
                LocalDate.of(2023, 7, 10), 40.5, 15);
        Food f3 = new Cheese("Poshekhonskiy", LocalDate.of(2023, 7, 25),
                LocalDate.of(2023, 7, 7), 30.5, 10);
        Food f4 = new Cheese("Kosichka", LocalDate.of(2023, 6, 1),
                LocalDate.of(2023, 1, 1), 30.5, 5);
        Shop.getInstance().add(f1);
        Shop.getInstance().add(f2);
        Shop.getInstance().add(f3);
        Shop.getInstance().add(f4);
        f3.setPrice(27.45);
        List<Food> expected = List.of(f2, f3);
        List<Food> fact = Shop.getInstance().getList();
        Shop.getInstance().clear();
        assertThat(fact).isEqualTo(expected);
    }
}