package ru.job4j.lsp.store;

import static ru.job4j.lsp.Constants.TRASH_QUALITY;
import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.util.ExpirationCalculator;
import ru.job4j.lsp.util.LocalDateTimeExpirationCalculator;

public class Trash extends AbstractStore {
       private final ExpirationCalculator<Food> expCalc;

    public Trash() {
        this.expCalc = new LocalDateTimeExpirationCalculator();
    }

    @Override
    public boolean isExpired(Food food) {
        return expCalc.count(food) <= TRASH_QUALITY;
    }
}