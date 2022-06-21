package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<Path, FileProperty> paths = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path files, BasicFileAttributes attrs) throws IOException {
        FileProperty file = new FileProperty(Files.size(files), files.getFileName().toString());
        if (paths.size() == 0) {
            paths.put(files.toAbsolutePath(), file);
        } else if (paths.containsValue(file)) {
            paths.put(files.toAbsolutePath(), file);
        }
        return super.visitFile(files, attrs);
    }

    public Map<Path, FileProperty> getPaths() {
        return paths;
    }
}