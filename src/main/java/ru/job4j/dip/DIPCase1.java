package ru.job4j.dip;

public class DIPCase1 {

    public class SimplePrint {
        public static void print(String string) {
            System.out.println(string);
        }
    }

    public class UI {
        public void sendMessage() {
            SimplePrint.print("Go cinema");
        }
    }
}