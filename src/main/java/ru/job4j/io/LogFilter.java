package ru.job4j.io;

import java.io.*;
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

    /** Метод принимает результат работы {@link #filter(String)}
     * и ссылку на файл.
     * Записывает полученный ArrayList из filter в файл.
     * @param log
     * @param file
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            log.forEach(l -> out.write(l + System.lineSeparator()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("SerialGC.txt");
        log.forEach(System.out::println);
        save(log, "404.txt");
        System.out.println("Log has saved to the file");
    }
}