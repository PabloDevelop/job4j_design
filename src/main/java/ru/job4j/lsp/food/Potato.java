package ru.job4j.lsp.food;

import java.time.LocalDateTime;

public class Potato extends Food {
    public Potato(String name, LocalDateTime expiryDate, LocalDateTime createDate, int price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}