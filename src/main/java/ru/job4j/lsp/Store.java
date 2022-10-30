package ru.job4j.lsp;

import java.util.function.Predicate;

public interface Store {
    void check(Food food);

    void add(Food food);
}