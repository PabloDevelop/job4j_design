package ru.job4j.isp.menu;

import java.util.List;
import java.util.Scanner;

public class TODOApp {
    private static final int MENU_LVL_FIRST = 1;
    private static final int MENU_LVL_SECOND = 2;
    private static final int MENU_LVL_THIRD = 3;
    public static final ActionDelegate STUB_ACTION = System.out::println;

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        Scanner input = new Scanner(System.in);
        MenuPrinter simpleMenuPrinter = new SimpleMenuPrinter();
        Menu simpleMenu = new SimpleMenu();
        boolean run = true;
        while (run) {
            showMenu();
            int select = Integer.parseInt(input.nextLine());
            if (select < MENU_LVL_FIRST || select > MENU_LVL_THIRD) {
                System.out.println("Некорректный ввод, введите пожалуйста в диапазоне: 1 - 3");
                continue;
            }
            run = actions(select, simpleMenu, simpleMenuPrinter, input);
        }
    }

    private static void showMenu() {
        List<String> options = List.of(" ", "Выберите действие:", "1. Создание задачи",
                "2. Вывод всех задач", "3. Выход");
        options.forEach(System.out::println);
    }

    private static boolean actions(int select, Menu simpleMenu, MenuPrinter simpleMenuPrinter, Scanner input) {
        boolean rsl = true;
        if (select == MENU_LVL_FIRST) {
            boolean add;
            System.out.println("- Параметры задачи -");
            System.out.println("Введите ROOT или имя родительской задачи:");
            String parentName = input.nextLine();
            System.out.println("Введите имя текущей задачи:");
            String currentName = input.nextLine();
            if (parentName.equals("ROOT")) {
                add = simpleMenu.add(Menu.ROOT, currentName, STUB_ACTION);
            } else {
                add = simpleMenu.add(parentName, currentName, STUB_ACTION);
            }
            System.out.println(add ? "Задача добавлена" : "Такая задача уже есть");
        } else if (select == MENU_LVL_SECOND) {
            System.out.println("Перечень текущих задач:");
            simpleMenuPrinter.print(simpleMenu);
        } else if (select == MENU_LVL_THIRD) {
            rsl = false;
            System.out.println("- Выход -");
        }
        return rsl;
    }
}