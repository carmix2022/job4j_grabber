package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;

public interface Store {
      boolean checkAndAdd(Food food, LocalDate localDate);

}
