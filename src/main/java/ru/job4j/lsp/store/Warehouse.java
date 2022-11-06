package ru.job4j.lsp.store;

import static ru.job4j.lsp.Constants.GOOD_QUALITY;
import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.util.ExpirationCalculator;
import java.time.LocalDateTime;

public class Warehouse extends AbstractStore {
    private final ExpirationCalculator<LocalDateTime> expCalc;

    public Warehouse(ExpirationCalculator<LocalDateTime> localDateTime) {
        this.expCalc = localDateTime;
    }

    @Override
    public boolean isExpired(Food food) {
        return expCalc.count(food.getExpiryDate(), food.getCreateDate()) > GOOD_QUALITY;
    }
}