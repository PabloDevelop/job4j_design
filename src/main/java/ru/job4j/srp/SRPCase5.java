package ru.job4j.srp;

public class SRPCase5 {
    private static SRPCase5 instance;

    private SRPCase5() {
    }

    public static SRPCase5 getInstance() {
        if (instance == null) {
            instance = new SRPCase5();
        }
        return instance;
    }
}