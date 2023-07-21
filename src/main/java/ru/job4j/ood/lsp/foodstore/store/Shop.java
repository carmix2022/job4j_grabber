package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.time.LocalDate;
import java.util.function.BiPredicate;

public class Shop extends AbstractStore {

    private static BiPredicate<Food, LocalDate> shopPredicate1 =
            (food, localDate) -> calcShareOfSpentFoodTime(food, localDate) >= TIME_SHARE_LOWER_LIMIT
            && calcShareOfSpentFoodTime(food, localDate) <= TIME_SHARE_MIDDLE_LIMIT;
    private static BiPredicate<Food, LocalDate> shopPredicate2 =
            (food, localDate) -> calcShareOfSpentFoodTime(food, localDate) > TIME_SHARE_MIDDLE_LIMIT
            && calcShareOfSpentFoodTime(food, localDate) < TIME_SHARE_UPPER_LIMIT;

    private static Shop instance = null;

    private Shop() {
    }
    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }

    @Override
    public boolean checkAndAdd(Food food, LocalDate localDate) {
        if (shopPredicate1.test(food, localDate)) {
            this.store.add(food);
            return true;
        }
        if (shopPredicate2.test(food, localDate)) {
            food.setPrice(Math.round(food.getPrice() * (100 - food.getDiscount())) / 100.0);
            this.store.add(food);
            return true;
        }
        return false;
    }
}
