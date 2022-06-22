package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path files, BasicFileAttributes attrs) throws IOException {
        FileProperty file = new FileProperty(Files.size(files), files.getFileName().toString());
        if (!map.containsKey(file)) {
            map.put(file, new ArrayList<>(List.of(files)));
        } else {
            map.get(file).add(files);
        }
        return super.visitFile(files, attrs);
    }

    public List<Path> getPaths() {
        return map.values()
                .stream()
                .filter(p -> p.size() > 1)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}