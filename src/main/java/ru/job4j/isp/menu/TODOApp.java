package ru.job4j.isp.menu;

import java.util.List;
import java.util.Scanner;

public class TODOApp {
    private static final Scanner INPUT = new Scanner(System.in);
    private static final Menu SIMPLE_MENU = new SimpleMenu();
    private static final MenuPrinter SIMPLE_MENU_PRINTER = new SimpleMenuPrinter();
    public static final ActionDelegate STUB_ACTION = System.out::println;

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        boolean run = true;
        while (run) {
            showMenu();
            int select = Integer.parseInt(INPUT.nextLine());
            if (select <= 0 || select > 3) {
                System.out.println("Некорректный ввод, введите пожалуйста в диапазоне: 1 - 3");
                continue;
            }
            run = actions(select);
        }
    }

    private static void showMenu() {
        List<String> options = List.of(" ", "Выберите действие:", "1. Создание задачи",
                "2. Вывод всех задач", "3. Выход");
        options.forEach(System.out::println);
    }

    private static boolean actions(int select) {
        boolean rsl = true;
        if (select == 1) {
            System.out.println("- Параметры задачи - ");
            System.out.println("Введите ROOT или имя родительской задачи:");
            String parentName = INPUT.nextLine();
            System.out.println("Введите имя текущей задачи:");
            String currentName = INPUT.nextLine();
            if (parentName.equals("ROOT")) {
                SIMPLE_MENU.add(Menu.ROOT, currentName, STUB_ACTION);
            } else {
                SIMPLE_MENU.add(parentName, currentName, STUB_ACTION);
            }
            System.out.println("Задача добавлена");
        } else if (select == 2) {
            System.out.println("Перечень текущий задач:");
            SIMPLE_MENU_PRINTER.print(SIMPLE_MENU);
        } else if (select == 3) {
            rsl = false;
            System.out.println("Выход");
        }
        return rsl;
    }
}