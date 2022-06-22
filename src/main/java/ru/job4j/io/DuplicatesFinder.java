package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(".");
        searchDuplicate(path)
                .forEach(System.out::println);

    }

    public static List<Path> searchDuplicate(Path path) throws IOException {
        DuplicatesVisitor searcher = new DuplicatesVisitor();
        Files.walkFileTree(path, searcher);
        return searcher.getPaths();
    }
}