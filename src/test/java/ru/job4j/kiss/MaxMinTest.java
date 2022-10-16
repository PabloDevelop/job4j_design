package ru.job4j.kiss;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Comparator;
import java.util.List;


class MaxMinTest {

    @Test
    void checkMin() {
        MaxMin min = new MaxMin();
        List<Integer> ints = List.of(1, 5, 7, 10, 18, 29);
        int rsl = min.min(ints, Comparator.naturalOrder());
        assertThat(rsl).isEqualTo(1);
    }

    @Test
    void checkMax() {
        MaxMin min = new MaxMin();
        List<Integer> ints = List.of(1, 5, 7, 10, 18, 29);
        int rsl = min.min(ints, Comparator.reverseOrder());
        assertThat(rsl).isEqualTo(29);
    }

    @Test
     void checkNull() {
        MaxMin min = new MaxMin();
        List<Integer> ints = List.of();
        assertThatThrownBy(() -> min.min(ints, Comparator.naturalOrder()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}