package ru.job4j.lsp.store;

import static ru.job4j.lsp.Constants.TRASH_QUALITY;
import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.util.ExpirationCalculator;
import java.time.LocalDateTime;

public class Trash extends AbstractStore {
       private final ExpirationCalculator<LocalDateTime> expCalc;

    public Trash(ExpirationCalculator<LocalDateTime> localDateTime) {
        this.expCalc = localDateTime;
    }

    @Override
    public boolean isExpired(Food food) {
        return expCalc.count(food.getExpiryDate(), food.getCreateDate()) <= TRASH_QUALITY;
    }
}