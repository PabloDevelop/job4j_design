package ru.job4j.lsp.store;

import static ru.job4j.lsp.Constants.GOOD_QUALITY;
import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.util.ExpirationCalculator;
import ru.job4j.lsp.util.LocalDateTimeExpirationCalculator;

public class Warehouse extends AbstractStore {
    private final ExpirationCalculator<Food> expCalc;

    public Warehouse() {
        this.expCalc = new LocalDateTimeExpirationCalculator();
    }

    @Override
    public boolean isExpired(Food food) {
        return expCalc.count(food) > GOOD_QUALITY;
    }
}