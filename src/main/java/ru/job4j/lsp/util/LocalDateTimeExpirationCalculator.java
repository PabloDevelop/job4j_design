package ru.job4j.lsp.util;

import static java.time.temporal.ChronoUnit.DAYS;
import java.time.LocalDateTime;

public class LocalDateTimeExpirationCalculator implements ExpirationCalculator<LocalDateTime> {
    @Override
    public double count(LocalDateTime expiryDate, LocalDateTime createDate) {
        return (DAYS.between(expiryDate, LocalDateTime.now()) * 100.00)
                / DAYS.between(expiryDate, createDate);
    }
}