package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;
import java.time.LocalDate;
import java.util.function.BiPredicate;

public class Trash extends AbstractStore {

    private static BiPredicate<Food, LocalDate> trashPredicate =
            (food, localDate) -> calcShareOfSpentFoodTime(food, localDate) >= TIME_SHARE_UPPER_LIMIT;
    private static Trash instance = null;

    private Trash() {
    }
    public static Trash getInstance() {
        if (instance == null) {
            instance = new Trash();
        }
        return instance;
    }

    @Override
    public boolean checkAndAdd(Food food, LocalDate localDate) {
         if (trashPredicate.test(food, localDate)) {
             this.store.add(food);
             return true;
         }
         return false;
    }
}
