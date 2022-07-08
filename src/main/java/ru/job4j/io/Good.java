package ru.job4j.io;

public class Good {
    private final String title;

    @Override
    public String toString() {
        return "Good{"
                + "title='" + title + '\''
                + '}';
    }

    public Good(String title) {
        this.title = title;
    }
}