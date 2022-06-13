package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    /** Метод прочитывает файл и
     * возвращае строки,
     * где предпоследнее значение - это 404
     * @param file
     * @return list
     */
    public List<String> filter(String file) {
        List<String> string = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            string = in.lines()
                    .filter(l -> l.contains(" 404 "))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}