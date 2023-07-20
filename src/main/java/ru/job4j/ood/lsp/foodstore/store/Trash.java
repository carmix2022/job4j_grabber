package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

import java.util.function.Predicate;

public class Trash extends AbstractStore {

    public static Predicate<Food> trashPredicate = food -> calcShareOfSpentFoodTime(food) >= 1;
    private static Trash instance = null;

    private Trash() {
    }
    public static Trash getInstance() {
        if (instance == null) {
            instance = new Trash();
        }
        return instance;
    }
}
