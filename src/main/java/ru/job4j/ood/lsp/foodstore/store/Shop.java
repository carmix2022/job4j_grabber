package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.util.function.Predicate;

public class Shop extends AbstractStore {

    public static Predicate<Food> shopPredicate1 = food -> calcShareOfSpentFoodTime(food) >= 0.25
            && calcShareOfSpentFoodTime(food) <= 0.75;
    public static Predicate<Food> shopPredicate2 = food -> calcShareOfSpentFoodTime(food) > 0.75
            && calcShareOfSpentFoodTime(food) < 1;

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
    public void add(Food food) {
        if (shopPredicate1.test(food)) {
            this.store.add(food);
        }
        if (shopPredicate2.test(food)) {
            food.setPrice(Math.round(food.getPrice() * (100 - food.getDiscount())) / 100.0);
            this.store.add(food);
        }
    }
}
