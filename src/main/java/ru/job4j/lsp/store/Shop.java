package ru.job4j.lsp.store;

import static ru.job4j.lsp.Constants.*;
import ru.job4j.lsp.food.Food;
import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore implements Store {
    private final List<Food> shopStorage = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        double expDays = count(food);
        if (expDays >= NOT_GOOD_QUALITY && expDays <= GOOD_QUALITY) {
            rsl = shopStorage.add(food);
        } else if (expDays > TRASH_QUALITY && expDays < NOT_GOOD_QUALITY) {
            food.setPrice(food.getPrice() - food.getDiscount());
            rsl = shopStorage.add(food);
        }
        return rsl;
    }

    @Override
    public List<Food> showAllFood() {
        return shopStorage;
    }
}