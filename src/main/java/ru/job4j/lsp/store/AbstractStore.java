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

    /**
     * Возвращает копию хранилища продуктов.
     *
     * @return List < Food >
     */
    @Override
    public List<Food> showAllFood() {
        return List.copyOf(storage);
    }

    /**
     * Возвращает копию хранилища продуктов
     * и полностью его очищает.
     *
     * @return List < Food >
     */
    @Override
    public List<Food> clear() {
        List<Food> list = showAllFood();
        storage.clear();
        return list;
    }
}