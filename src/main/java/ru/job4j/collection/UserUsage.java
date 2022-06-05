package ru.job4j.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserUsage {
    public static void main(String[] args) {
        Map<User, Object> users = new HashMap<>();
        User user1 = new User("Vasya", 1,
                new GregorianCalendar(2018, Calendar.JANUARY, 5));
        User user2 = new User("Vasya", 1,
                new GregorianCalendar(2018, Calendar.JANUARY, 5));
        users.put(user1, new Object());
        users.put(user2, new Object());
        System.out.println(users.entrySet());
    }
}