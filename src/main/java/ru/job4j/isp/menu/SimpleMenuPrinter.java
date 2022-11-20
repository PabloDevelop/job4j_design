package ru.job4j.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    private static final String TAB = "--";

    /**
     * Вычисляет интовое значение уровня вложенности.
     * Добавляет к выводу количество отступов пропорционально значению уровня.
     *
     * @param menu
     */
    @Override
    public void print(Menu menu) {
        menu.forEach(i -> {
            int count = i.getNumber().split("\\.").length - 1;
            System.out.printf("%s%s %s%n", TAB.repeat(count), i.getNumber(), i.getName());
        });
    }
}