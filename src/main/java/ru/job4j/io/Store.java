package ru.job4j.io;

import java.util.Arrays;

public class Store {
    private final boolean open;
    private final int price;
    private final Good good;
    private final String[] cashBoxes;

    public Store(boolean open, int price, Good good, String[] cashBoxes) {
        this.open = open;
        this.price = price;
        this.good = good;
        this.cashBoxes = cashBoxes;
    }

    @Override
    public String toString() {
        return "Store{"
                + "open=" + open
                + ", price=" + price
                + ", good=" + good
                + ", cashBoxes=" + Arrays.toString(cashBoxes)
                + '}';
    }
}