package ru.job4j.ood.lsp.foodstore;

import ru.job4j.ood.lsp.foodstore.food.Food;
import ru.job4j.ood.lsp.foodstore.store.Store;

public class ControlQuality {

    static Store store;

    public void controlQuality(Food food) {
        store = Store.check(food);
        if (store != null) {
            store.add(food);
        } else {
            throw new IllegalArgumentException("подходящего хранилища не найдено");
        }
    }
}
