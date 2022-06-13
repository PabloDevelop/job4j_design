package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

/** Метод прочитывает файл even.txt.
 * Для каждого числа проверяет является ли оно четным.
 * Ответ выводит на консоль.
 */
public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            for (String line : text.toString().split(System.lineSeparator())) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(line + " - четное");
                } else {
                    System.out.println(line + " - не четное");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}