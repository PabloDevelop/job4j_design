package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {
    /**Вставляет до индекса.
     * @param list
     * @param index
     * @param value
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator(index);
        iterator.add(value);
    }

    /**Вставляет после индекса.
     * @param list
     * @param index
     * @param value
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator(index + 1);
        iterator.add(value);
    }

    /**Удаляет все элементы, которые удовлетворяют предикату.
     * @param list
     * @param filter
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**Заменяет все элементы, которые удовлетворяют предикату.
     * @param list
     * @param filter
     * @param value
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    /**Удаляет из списка те элементы, которые есть в elements.
     * @param list
     * @param elements
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iteratorList = list.listIterator();
        ListIterator<T> iteratorElem = elements.listIterator();
        int minSize = Math.min(list.size(), elements.size());
        for (int i = 0; i < minSize; i++) {
            if (iteratorList.next() == iteratorElem.next()) {
            iteratorList.remove();
            }
        }
    }
}