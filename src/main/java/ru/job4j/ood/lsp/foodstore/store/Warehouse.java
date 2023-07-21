package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;
import java.util.function.BiPredicate;

public class Warehouse extends AbstractStore {

    private static BiPredicate<Food, LocalDate> warehousePredicate =
            (food, localDate) -> calcShareOfSpentFoodTime(food, localDate) < TIME_SHARE_LOWER_LIMIT;
    private static Warehouse instance = null;

    private Warehouse() {
    }
    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    @Override
    public boolean checkAndAdd(Food food, LocalDate localDate) {
        if (warehousePredicate.test(food, localDate)) {
            this.store.add(food);
            return true;
        }
        return false;
    }
}
