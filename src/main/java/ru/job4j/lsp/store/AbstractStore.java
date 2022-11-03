package ru.job4j.lsp.store;

import static java.time.temporal.ChronoUnit.DAYS;
import ru.job4j.lsp.food.Food;
import java.time.LocalDateTime;

public abstract class AbstractStore implements Store {
    protected static double count(Food food) {
        return (DAYS.between(food.getExpiryDate(), LocalDateTime.now()) * 100.00)
                / DAYS.between(food.getExpiryDate(), food.getCreateDate());
    }
}