package ru.job4j.srp.report;

import com.google.gson.Gson;
import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.Store;
import java.util.function.Predicate;

public class JSONReport implements Report {

    private final Store store;
    private final Gson gsonBuilder;

    public JSONReport(Store store) {
        this.store = store;
        gsonBuilder = new Gson();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gsonBuilder.toJson(store.findBy(filter));
    }
}