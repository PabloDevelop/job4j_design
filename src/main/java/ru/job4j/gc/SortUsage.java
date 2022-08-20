package ru.job4j.gc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SortUsage {
    private static Scanner scanner = new Scanner(System.in);
    private static Data randomArray = new RandomArray(new Random());
    private static InsertSort insertSort = new InsertSort();
    private static BubbleSort bubbleSort = new BubbleSort();
    private static MergeSort mergeSort = new MergeSort();

    public static void main(String[] args) {
        System.out.println("Enter elements count:");
        init(Integer.parseInt(scanner.nextLine()));
    }

    private static void init(int number) {
        boolean run = true;
        while (run) {
            showMenu();
            int select = Integer.parseInt(scanner.nextLine());
            if (select < 0 || select > 5) {
                System.out.println("Wrong input, you can select: 0 - 5");
                continue;
            }
            run = actions(number, select);
        }
    }

    private static void showMenu() {
        List<String> options = List.of(" ", "Select action:", "1. Создание массива",
                "2. Сортировка пузырьком", "3. Сортировка вставками",
                "4. Сортировка слиянием", "5. Выход");
        options.forEach(System.out::println);
    }

    private static String time() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    private static boolean actions(int number, int select) {
        boolean rsl = true;
        if (select == 1) {
            System.out.printf("%s %s %n", "Создание массива", time());
            randomArray.insert(number);
            System.out.printf("%s %s %n", "Массив создан", time());
        } else if (select == 2) {
            System.out.printf("%s %s %n", "Начата сортировка пузырьком", time());
            rsl = bubbleSort.sort(randomArray);
            System.out.printf("%s %s %n", "Массив отсортирован пузырьком", time());
        } else if (select == 3) {
            System.out.printf("%s %s %n", "Начата сортировка вставками", time());
            rsl = insertSort.sort(randomArray);
            System.out.printf("%s %s %n", "Массив отсортирован вставками", time());
        } else if (select == 4) {
            System.out.printf("%s %s %n", "Начата сортировка слиянием", time());
            rsl = mergeSort.sort(randomArray);
            System.out.printf("%s %s %n", "Массив отсортирован слиянием", time());
        } else if (select == 5) {
            rsl = false;
            System.out.println("Выход");
        }
        return rsl;
    }
}