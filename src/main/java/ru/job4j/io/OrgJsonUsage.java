package ru.job4j.io;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrgJsonUsage {
    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        JSONObject jsonGood = new JSONObject("{\"title\":\"Bread\"}");
        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("5 people");
        list.add("6 people");
        JSONArray jsonStatuses = new JSONArray(list);
        /* JSONObject напрямую методом put */
        final Store store = new Store(true, 45, new Good("Bread"),
                new String[] {"3 people", "4 people"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("open", store.isOpen());
        jsonObject.put("price", store.getPrice());
        jsonObject.put("good", jsonGood);
        jsonObject.put("cashBoxes", jsonStatuses);
        /* Выведем результат в консоль */
        System.out.println(jsonObject);
        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(store));
    }
}