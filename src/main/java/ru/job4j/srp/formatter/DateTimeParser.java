package ru.job4j.srp.formatter;

public interface DateTimeParser<T> {
    String parse(T t);
}