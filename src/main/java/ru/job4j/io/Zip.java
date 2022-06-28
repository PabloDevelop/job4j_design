package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    /**Принимает лист путей и путь целевого файла.
     * В форич перебирает переданные в листе пути,
     * добавляет их в ZipEntry.
     * @param sources
     * @param target
     */
    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target.toString())))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
            try (BufferedInputStream out = new BufferedInputStream(
                    new FileInputStream(target.toString()))) {
                    zip.write(out.readAllBytes());
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**Проверяет входные аргументы на корректность.
     * Если параметры на корректны, то выкидывает исключение.
     * @param p результат работы ArgsName
     * @return true
     */
    private static boolean validation(ArgsName p) {
        Path path = Path.of(p.get("d"));
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException(path + " isn't exist.");
        }
        if (!p.get("e").startsWith(".")) {
            throw new IllegalArgumentException(p.get("e") + " is wrong argument.");
        }
        if (!p.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException(p.get("o") + " is wrong argument.");
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        ArgsName parametrs = ArgsName.of(args);
        if (Zip.validation(parametrs)) {
            List<Path> list = Search.search(Path.of(parametrs.get("d")),
                    p -> !p.toFile().getName().endsWith(parametrs.get("e")));
            Zip zip = new Zip();
            zip.packFiles(list, Path.of(parametrs.get("o")));
        }
    }
}