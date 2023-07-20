package ru.job4j.ood.lsp.foodstore.store;

import ru.job4j.ood.lsp.foodstore.food.Food;

public interface Store {
     static Store check(Food food) {
        return AbstractStore.check(food);
    }

     void add(Food food);
}
