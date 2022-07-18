package ru.job4j.findfiles;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchVisitor extends SimpleFileVisitor<Path> {
    List<Path> paths = new ArrayList<>();
    Predicate<Path> predicate;

    public SearchVisitor(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    @Override
    public FileVisitResult visitFile(Path files, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(files)) {
            paths.add(files);
        }
        return super.visitFile(files, attrs);
    }

    public List<Path> getPaths() {
        return paths;
    }
}