package ru.job4j.lsp;

import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.store.Store;

import java.util.*;

public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void sortFood(Food food) {
        for (Store store : stores) {
            if (store.add(food)) {
                break;
            }
        }
    }

    public void resort() {
        List<Food> foodCache = new ArrayList<>();
        for (Store store : stores) {
            foodCache.addAll(store.clear());
        }
        foodCache.forEach(this::sortFood);
    }
}