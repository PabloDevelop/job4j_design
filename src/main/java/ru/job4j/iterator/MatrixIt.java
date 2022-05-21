package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**Если значение индекса строки меньше размера массива,
     * а значение индекса слобца равно его длине,
     * то увеличивает индекс строки на 1
     * и присваивает индексу столбца значение 0.
     * @return true если индекс строки меньше размера массива, иначе false
     */
    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == column) {
            row++;
            column = 0;
        }
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}