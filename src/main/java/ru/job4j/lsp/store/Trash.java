package ru.job4j.lsp.store;

import static ru.job4j.lsp.Constants.TRASH_QUALITY;
import ru.job4j.lsp.food.Food;
import java.util.ArrayList;
import java.util.List;

public class Trash extends AbstractStore implements Store {
    private final List<Food> trashStorage = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        double expDays = count(food);
        if (expDays <= TRASH_QUALITY) {
            rsl = trashStorage.add(food);
        }
        return rsl;
    }

    @Override
    public List<Food> showAllFood() {
        return trashStorage;
    }
}