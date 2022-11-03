package ru.job4j.lsp.store;

import static ru.job4j.lsp.Constants.GOOD_QUALITY;
import ru.job4j.lsp.food.Food;
import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AbstractStore implements Store {
    private final List<Food> warehouseStorage = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        double expDays = count(food);
        if (expDays > GOOD_QUALITY) {
            rsl = warehouseStorage.add(food);
        }
        return rsl;
    }

    @Override
    public List<Food> showAllFood() {
        return warehouseStorage;
    }
}