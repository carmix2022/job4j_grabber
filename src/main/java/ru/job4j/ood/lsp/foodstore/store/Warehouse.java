package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.util.function.Predicate;

public class Warehouse extends AbstractStore {

    public static Predicate<Food> warehousePredicate = food -> calcShareOfSpentFoodTime(food) < 0.25;
    private static Warehouse instance = null;

    private Warehouse() {
    }
    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }
}
