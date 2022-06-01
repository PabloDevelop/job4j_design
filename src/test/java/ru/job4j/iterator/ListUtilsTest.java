package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoved() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        Predicate<Integer> predicate = v -> v > 3;
        ListUtils.removeIf(input, predicate);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenReplaced() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        Predicate<Integer> predicate = v -> v > 3;
        ListUtils.replaceIf(input, predicate, 10);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3, 10, 10)));
    }

    @Test
    public void whenRemovedAll() {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        List<Integer> inputElements = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.removeAll(inputList, inputElements);
        assertThat(inputList, is(Arrays.asList(3, 4, 5)));
    }
}