package ru.job4j.lsp.store;

import static ru.job4j.lsp.Constants.*;
import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.util.ExpirationCalculator;
import ru.job4j.lsp.util.LocalDateTimeExpirationCalculator;

public class Shop extends AbstractStore {
    private final ExpirationCalculator<Food> expCalc;

    public Shop() {
        this.expCalc = new LocalDateTimeExpirationCalculator();
    }

    @Override
    public boolean isExpired(Food food) {
        boolean rsl = false;
        double expDays = expCalc.count(food);
        if (expDays >= NOT_GOOD_QUALITY && expDays <= GOOD_QUALITY) {
            rsl = true;
        } else if (expDays > TRASH_QUALITY && expDays < NOT_GOOD_QUALITY) {
            food.setPrice(food.getPrice() - food.getDiscount());
            rsl = true;
        }
        return rsl;
    }
}