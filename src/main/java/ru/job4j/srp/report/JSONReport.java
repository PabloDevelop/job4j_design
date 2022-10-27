package ru.job4j.srp.report;

import com.google.gson.Gson;
import ru.job4j.srp.formatter.DateTimeParser;
import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class JSONReport implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Gson gsonBuilder;

    public JSONReport(Store store, DateTimeParser<Calendar> dateTimeParser, Gson gsonBuilder) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.gsonBuilder = gsonBuilder;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gsonBuilder.toJson(store.findBy(filter));
    }
}