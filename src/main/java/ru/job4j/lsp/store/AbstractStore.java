package ru.job4j.lsp.store;

import ru.job4j.lsp.food.Food;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {
    private final List<Food> storage = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        return isExpired(food) ? storage.add(food) : false;

    }
    @Override
    public List<Food> showAllFood() {
        return List.copyOf(storage);
    }
}