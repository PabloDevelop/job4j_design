package ru.job4j.gc;

import java.util.Random;
import java.util.Scanner;

public class SortUsage {
    private static Scanner scanner = new Scanner(System.in);
    private static Data randomArray = new RandomArray(new Random());
    private static InsertSort insertSort = new InsertSort();
    private static BubbleSort bubbleSort = new BubbleSort();
    private static MergeSort mergeSort = new MergeSort();

    public static void main(String[] args) {
        System.out.println("Enter element count: ");
        int number = Integer.parseInt(scanner.nextLine());
        init(number);
    }

    public static void init(int number) {
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

    public static void showMenu() {
        System.out.println("Select action: ");
        System.out.println("1. Создание массива");
        System.out.println("2. Сортировка пузырьком");
        System.out.println("3. Сортировка вставками");
        System.out.println("4. Сортировка слиянием");
        System.out.println("5. Выход");
    }

    public static boolean actions(int number, int select) {
        boolean rsl = true;
        if (select == 1) {
            randomArray.insert(number);
            System.out.println("Массив создан");
        } else if (select == 2) {
            rsl = bubbleSort.sort(randomArray);
            System.out.println("Массив отсортирован пузырьком");
        } else if (select == 3) {
            rsl = insertSort.sort(randomArray);
            System.out.println("Массив отсортирован вставками");
        } else if (select == 4) {
            rsl = mergeSort.sort(randomArray);
            System.out.println("Массив отсортирован слиянием");
        } else if (select == 5) {
            rsl = false;
            System.out.println("Выход");
        }
        return rsl;
    }
}