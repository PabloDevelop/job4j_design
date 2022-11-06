package ru.job4j.lsp.util;

public interface ExpirationCalculator<T> {
    double count(T t);
}