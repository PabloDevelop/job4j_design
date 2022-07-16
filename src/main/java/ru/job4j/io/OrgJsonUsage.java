package ru.job4j.io;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrgJsonUsage {
    public static void main(String[] args) {
        JSONObject jsonGood = new JSONObject("{\"title\":\"Bread\"}");
        List<String> list = new ArrayList<>();
        list.add("5 people");
        list.add("6 people");
        JSONArray jsonStatuses = new JSONArray(list);
        final Store store = new Store(true, 45, new Good("Bread"),
                new String[] {"3 people", "4 people"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("open", store.isOpen());
        jsonObject.put("price", store.getPrice());
        jsonObject.put("good", jsonGood);
        jsonObject.put("cashBoxes", jsonStatuses);
        System.out.println(jsonObject);
        System.out.println(new JSONObject(store));
    }
}