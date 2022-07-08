package ru.job4j.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class StoreUsage {
    public static void main(String[] args) throws IOException {
        final Store store = new Store(true, 45, new Good("Bread"),
                new String[] {"3 people", "4 people"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(store));
        final String storeJson =
                "{"
                        + "\"open\":true,"
                        + "\"price\":45,"
                        + "\"good\":"
                        + "{"
                        + "\"title\":\"Bread\""
                        + "},"
                        + "\"cashBoxes\":"
                        + "[\"3 people\",\"4 people\"]"
                        + "}";
        final Store store1 = gson.fromJson(storeJson, Store.class);
        System.out.println(store1);
    }
}