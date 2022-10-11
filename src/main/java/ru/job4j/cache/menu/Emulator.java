package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {
    public static final int SET_DIRECTORY = 1;
    public static final int LOAD_CACHE = 2;
    public static final int GET_FILE = 3;
    private final Scanner scanner = new Scanner(System.in);
    private DirFileCache dirCache;

    public static final String MENU = """
                Введите 1 для указания кешируемой директории.
                Введите 2 для загрузки содержимого файла в кеш.
                Введите 3 для получения содержимого файла из кэша.
                Введите 4 для выхода.
            """;

    private void start() {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println("Ввод:");
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (SET_DIRECTORY == userChoice) {
                System.out.println("Введите директорию:");
                dirCache = new DirFileCache(scanner.nextLine());
            } else if (LOAD_CACHE == userChoice) {
                System.out.println("Введите название файла:");
                String fileName = scanner.nextLine();
                dirCache.put(fileName, dirCache.get(fileName));
            } else if (GET_FILE == userChoice) {
                System.out.println("Введите название для получения содержимого:");
                String filename = scanner.nextLine();
                System.out.println(dirCache.get(filename));
            } else {
                run = false;
                System.out.println("Выход");
            }
        }
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        emulator.start();
    }
}
