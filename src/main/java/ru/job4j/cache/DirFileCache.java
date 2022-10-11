package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder rsl = new StringBuilder();
        try (Scanner in = new Scanner(Path.of(String.format("%s%s", cachingDir, key)))) {
            while (in.hasNext()) {
                rsl.append(String.format("%s %n", in.nextLine()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rsl.toString();
    }
}