package ru.job4j.findfiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(key + " isn't exist.");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Array is empty.");
        }
        for (String s : args) {
            List<String> parsedString = ArgsName.validate(s);
                values.put(parsedString.get(0), parsedString.get(1));
        }
    }

    public static List<String> validate(String args) {
        List<String> splitLines;
        if (!args.startsWith("-") || !args.contains("=")) {
            throw new IllegalArgumentException(args + " doesn't contain \"-\" or \"=\"");
        }
        splitLines = List.of(args.replace("-", "").split("=", 2));
        if (splitLines.get(0).isEmpty() || splitLines.get(1).isEmpty()) {
            throw new IllegalArgumentException("Key or value is empty.");
        }
        return splitLines;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}