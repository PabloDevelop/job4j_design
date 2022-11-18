package ru.job4j.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    private final static int DIVIDER = 2;

    /**
     * Вычисляет интовое значение уровня вложенности задачи путем деления
     * количества цифр в номере задачи на минимальную длину номера задачи.
     * Добавляет к выводу количество отступов пропорционально значению уровня.
     *
     * @param menu
     */
    @Override
    public void print(Menu menu) {
        menu.forEach(i -> {
            int level = i.getNumber().length() / DIVIDER;
            String tab = "";
            if (level > 1) {
                tab = "--";
                System.out.printf("%n%s%s %s", tab.repeat(level), i.getNumber(), i.getName());
            } else {
                System.out.printf("%n%s%s %s", tab, i.getNumber(), i.getName());
            }
        });
    }
}