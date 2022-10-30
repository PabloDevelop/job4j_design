package ru.job4j.lsp;

public class ControlQuality {
    private Store store;

    public ControlQuality(Store store) {
        this.store = store;
    }

    private void checkFood(Food food) {
       store.check(food);
    }
}