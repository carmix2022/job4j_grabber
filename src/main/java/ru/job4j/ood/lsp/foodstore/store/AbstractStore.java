package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    protected List<Food> store = new ArrayList<>();
    protected static final double TIME_SHARE_UPPER_LIMIT = 1.0;
    protected static final double TIME_SHARE_MIDDLE_LIMIT = 0.75;
    protected static final double TIME_SHARE_LOWER_LIMIT = 0.25;

    public List<Food> getList() {
        return List.copyOf(store);
    }

    public void clear() {
        store.clear();
    }

    protected static double calcShareOfSpentFoodTime(Food food, LocalDate localDate) {
        long expirationTime = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        long spentTime = ChronoUnit.DAYS.between(food.getCreateDate(), localDate);
        return 1.0 * spentTime / expirationTime;
    }
}
