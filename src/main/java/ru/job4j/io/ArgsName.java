package ru.job4j.io;

import java.util.*;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("The key isn't exist.");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String s : args) {
            List<String> parsedString = ArgsName.validate(s);
                values.put(parsedString.get(0), parsedString.get(1));
        }
    }

    public static List<String> validate(String args) {
        List<String> splitLines = new ArrayList<>();
        if (!args.startsWith("-") || !args.contains("=")) {
            throw new IllegalArgumentException("Wrong data.");
        }
        splitLines.add(args.split("=", 2)[0].split("-", 2)[1]);
        splitLines.add(args.split("=", 2)[1]);
        if (splitLines.get(0).isEmpty() || splitLines.get(1).isEmpty()) {
            throw new IllegalArgumentException("Wrong data.");
        }
        return splitLines;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}