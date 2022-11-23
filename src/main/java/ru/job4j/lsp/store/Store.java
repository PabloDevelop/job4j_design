package ru.job4j.lsp.store;

import ru.job4j.lsp.food.Food;
import java.util.List;

public interface Store {
    boolean add(Food food);

    boolean isExpired(Food food);

    List<Food> showAllFood();

    List<Food> clear();
}