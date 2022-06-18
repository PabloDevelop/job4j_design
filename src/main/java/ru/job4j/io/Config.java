package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /** должен считать все ключи в карту values.
     * Важно в файле могут быть пустые строки
     * и комментарии их нужно пропускать.
     */
    public void load() throws IllegalArgumentException {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            while (read.ready()) {
                String line = read.readLine();
                if (!line.startsWith("#") && line.length() > 0) {
                    String[] splitlines = line.split("=", 2);
                    if (splitlines.length != 2 || line.startsWith("=")) {
                        throw new IllegalArgumentException();
                    }
                    values.put(splitlines[0], splitlines[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./io.data/app.properties"));
    }
}