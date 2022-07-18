package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class SearchFile {

    /**Последовательно вызывает методы класса.
     * @param argsName параметры запуска
     */
    public static void activate(ArgsName argsName) {
        SearchFile.validation(argsName);
        List<Path> result = searchFile(Path.of(argsName.get("d")));
        writeOut(argsName.get("o"), result);
    }

    /**Ищет файл в указанной директории.
     * @param path
     * @return
     */
    public static List<Path> searchFile(Path path) {
        SearchVisitor searcher = new SearchVisitor();
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
                out.write(rsl.toString());
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
            throw new IllegalArgumentException(p.get("t") + " is wrong file type.");
        }
        if (!p.get("n").contains("*.?xt")) {
            throw new IllegalArgumentException(p.get("n") + " is incorrect file name.");
        }
        if (!Paths.get(p.get("o")).toFile().exists()) {
            throw new IllegalArgumentException(p.get("o") + " there isn't any out parameter.");
        }
    }

    public static void main(String[] args) {
        activate(ArgsName.of(args));
    }
}