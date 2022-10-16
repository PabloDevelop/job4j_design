package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> predicate = (t, t1) -> comparator.compare(t, t1) > 0;
        return searcher(value, predicate);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> predicate = (t, t1) -> comparator.compare(t, t1) < 0;
        return searcher(value, predicate);
    }

    private <T> T searcher(List<T> value, BiPredicate<T, T> predicate) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException();
        }
        T temp = value.get(0);
        for (T t : value) {
            if (predicate.test(t, temp)) {
                temp = t;
            }
        }
        return temp;
    }
}