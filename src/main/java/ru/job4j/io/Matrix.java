package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class Matrix {
    public static void main(String[] args) throws IOException {
        FileOutputStream out = new FileOutputStream("result.txt");
        int[][] matrix = new int[5][5];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
                out.write(String.valueOf(matrix[i][j]).getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        }
    }
}