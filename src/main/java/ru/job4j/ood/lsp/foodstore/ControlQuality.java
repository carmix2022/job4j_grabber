package ru.job4j.ood.lsp.foodstore;

import ru.job4j.ood.lsp.foodstore.food.Food;
import ru.job4j.ood.lsp.foodstore.store.Store;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ControlQuality {

    private static List<Store> storeList = new ArrayList<>();

    public ControlQuality() {
    }

    public ControlQuality(List<Store> stores) {
        storeList.addAll(stores);
    }

    public void controlQuality(List<Food> foodList, LocalDate localDate) {
        for (Food food : foodList) {
            for (Store store : storeList) {
                if (store.checkAndAdd(food, localDate)) {
                    break;
                }
            }
        }
    }

    public void resort(LocalDate localDate) {
        List<Food> foodList = storeList.stream()
                .flatMap(store -> store.getList().stream())
                .toList();
        storeList.forEach(Store::clear);
        controlQuality(foodList, localDate);
    }
}
