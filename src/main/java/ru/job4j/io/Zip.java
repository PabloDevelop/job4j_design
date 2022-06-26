package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

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

        public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        List<Path> list = new ArrayList<>(Search.search(Path.of("./"), p -> p.toFile().getName().endsWith(".txt")));
        Zip zip = new Zip();
        zip.packFiles(list, Path.of("./list.zip"));
        }
}