package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    private static List<String[]> input = new ArrayList<>();

    /**Прниимает на вход объект ArgsName.
     * Последовательно вызывает методы.
     * @param argsName
     */
    public static void handle(ArgsName argsName) {
        CSVReader.validation(argsName);
        writeIn(Path.of(argsName.get("path")), argsName.get("delimiter"));
        List<String> filter = filterArgs(argsName);
        StringBuilder result = filterUsage(filter, argsName.get("delimiter"), Path.of(argsName.get("path")));
        writeOut(argsName.get("out"), result);
    }

    private static StringBuilder filterUsage(List<String> filter, String delimiter, Path path) {
        StringBuilder result = new StringBuilder();
        try (Scanner in = new Scanner(path)) {
            var titles = in.nextLine().split(delimiter);
            for (var d : input) {
                StringBuilder str = new StringBuilder();
                for (int i = 0; i < titles.length; i++) {
                    if (filter.contains(titles[i])) {
                        str.append(str.length() == 0 ? d[i] : delimiter + d[i] + System.lineSeparator());
                    }
                }
                result.append(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**Принимает на вход объект ArgsName.
     * Заносит аргументы из параметра filter в list.
     * @param argsName
     * @return list с аргументами
     */
    private static List<String> filterArgs(ArgsName argsName) {
        return List.of(argsName.get("filter").split(","));
    }

    /**Принимает на вход путь к файлу CSV
     * и используемый разделитель.
     * Записывает строки из файла в ArrayList,
     * содержащий массивы String.
     * @param path путь к читаемому файлу
     * @param delimiter используемый разделитель
     */
    private static void writeIn(Path path, String delimiter) {
        try (Scanner in = new Scanner(path)) {
            while (in.hasNext()) {
                input.add(in.nextLine().split(delimiter));
            }
            System.out.println("The CSV-file has been read.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Принимает на вход параметр вывода
     * и строковое представление отфильтрованной информации.
     * Если параметр stdout, то выводит на консоль,
     * если параметр путь к файлу, то записывает в файл.
     * @param outParameter параметр вывода
     * @param stringBuilder выводимая информация
     */
    private static void writeOut(String outParameter, StringBuilder stringBuilder) {
        if ("stdout".contains(outParameter)) {
            System.out.println(stringBuilder.toString());
        } else {
            try (PrintWriter out = new PrintWriter(
                            new FileWriter(outParameter))) {
                out.write(stringBuilder.toString());
                System.out.println("Filtered data has been written to a file.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**Проверяет параметры запуска на корректность.
     * @param p объект ArgsName с ключами и значениями
     */
    private static void validation(ArgsName p) {
        if (!Paths.get(p.get("path")).toFile().exists()) {
            throw new IllegalArgumentException(p.get("path") + " isn't exist.");
        }
        if (!p.get("delimiter").endsWith(";")) {
            throw new IllegalArgumentException(p.get("delimiter") + " is wrong delimiter parameter.");
        }
        if (p.get("filter").isEmpty()) {
            throw new IllegalArgumentException(p.get("filter") + " there isn't any filter.");
        }
        if (p.get("out").isEmpty()) {
            throw new IllegalArgumentException(p.get("out") + " there isn't any out parameter.");
        }
    }

    public static void main(String[] args) {
        handle(ArgsName.of(args));
    }
}