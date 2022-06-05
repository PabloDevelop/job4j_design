package ru.job4j.collection;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserUsage {
    public static void main(String[] args) {
        Map<User, Object> users = new HashMap<>();
        Calendar calendar1 = new GregorianCalendar();
        Calendar calendar2 = new GregorianCalendar();
        calendar1.set(Calendar.YEAR, 2018);
        calendar1.set(Calendar.MONTH, 1);
        calendar1.set(Calendar.DAY_OF_MONTH, 5);
        calendar1.set(Calendar.HOUR_OF_DAY, 22);
        calendar1.set(Calendar.MINUTE, 10);
        calendar1.set(Calendar.SECOND, 10);
        calendar1.set(Calendar.MILLISECOND, 10);
        calendar2.set(Calendar.YEAR, 2018);
        calendar2.set(Calendar.MONTH, 1);
        calendar2.set(Calendar.DAY_OF_MONTH, 5);
        calendar2.set(Calendar.HOUR_OF_DAY, 22);
        calendar2.set(Calendar.MINUTE, 10);
        calendar2.set(Calendar.SECOND, 10);
        calendar2.set(Calendar.MILLISECOND, 10);
        User user1 = new User("Vasya", 1, calendar1);
        User user2 = new User("Vasya", 1, calendar2);
        users.put(user1, new Object());
        users.put(user2, new Object());
        System.out.println(users.entrySet());
    }
}