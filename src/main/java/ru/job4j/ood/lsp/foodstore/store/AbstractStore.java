package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    protected List<Food> store = new ArrayList<>();
    public void add(Food food) {
        store.add(food);
    }

    public List<Food> getList() {
        return List.copyOf(store);
    }

    public void clear() {
        store.clear();
    }

    protected static double calcShareOfSpentFoodTime(Food food) {
        long expirationTime = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long spentTime = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        return 1.0 * spentTime / expirationTime;
    }

    public static Store check(Food food) {
        if (Warehouse.warehousePredicate.test(food)) {
            return Warehouse.getInstance();
        }
        if (Shop.shopPredicate1.test(food) || Shop.shopPredicate2.test(food)) {
            return Shop.getInstance();
        }
        if (Trash.trashPredicate.test(food)) {
            return Trash.getInstance();
        }
        return null;
    }
}
