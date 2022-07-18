package ru.job4j.findfiles;

import ru.job4j.io.ArgsName;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class SearchFile {

    /**Реализует логику поиска по параметру типа поиска.
     * @param argsName параметры запуска
     */
    public static void activate(ArgsName argsName) {
        SearchFile.validation(argsName);
        List<Path> result;
        if ("name".contains(argsName.get("t"))) {
            result = searchFile(Path.of(argsName.get("d")), path ->
                path.toFile().getName().contains(argsName.get("n")));
            writeOut(argsName.get("o"), result);
        } else if ("regex".contains(argsName.get("t"))) {
            PathMatcher pathMatcher = FileSystems.getDefault()
                    .getPathMatcher("regex:" + argsName.get("n"));
            result = searchFile(Path.of(argsName.get("d")), path ->
                    pathMatcher.matches(Paths.get(path.toFile().getName())));
            writeOut(argsName.get("o"), result);
        } else if ("mask".contains(argsName.get("t"))) {
            PathMatcher pathMatcher = FileSystems.getDefault()
                    .getPathMatcher("glob:" + argsName.get("n"));
            result = searchFile(Path.of(argsName.get("d")), path ->
                    pathMatcher.matches(Paths.get(path.toFile().getName())));
            writeOut(argsName.get("o"), result);
        }
    }

    /**Ищет файл по условию в указанной директории.
     * @param path директория поиска
     * @param predicate условие поиска
     * @return
     */
    public static List<Path> searchFile(Path path, Predicate<Path> predicate) {
        SearchVisitor searcher = new SearchVisitor(predicate);
        try {
            Files.walkFileTree(path, searcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searcher.getPaths();
    }

    /**Записывает найденные пути к файлам в указанный файл.
     * @param path файл для записи
     * @param result пути к найденным файлам
     */
    private static void writeOut(String path, List<Path> result) {
        try (PrintWriter out = new PrintWriter(
                new FileWriter(path))) {
            for (Path rsl : result) {
                out.write(rsl.toString() + System.lineSeparator());
            }
            System.out.println("Searched paths have been written to a file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Проверяет параметры запуска на корректность.
     * @param p объект ArgsName с ключами и значениями
     */
    private static void validation(ArgsName p) {
        if (!Paths.get(p.get("d")).toFile().exists()) {
            throw new IllegalArgumentException(p.get("d") + " isn't exist.");
        }
        if (!p.get("t").contains("mask")) {
            throw new IllegalArgumentException(p.get("t") + " is wrong search parameter.");
        }
        if (!p.get("n").contains("*.?xt")) {
            throw new IllegalArgumentException(p.get("n") + " is incorrect file name.");
        }
        if (p.get("o").isEmpty()) {
            throw new IllegalArgumentException(p.get("o") + " there isn't any out parameter.");
        }
    }

    public static void main(String[] args) {
        activate(ArgsName.of(args));
    }
}