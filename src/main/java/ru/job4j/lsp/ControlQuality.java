package ru.job4j.lsp;

import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.store.Store;
import java.util.List;

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
}