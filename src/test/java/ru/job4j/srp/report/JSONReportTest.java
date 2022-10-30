package ru.job4j.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.MemStore;
import java.util.Calendar;
import static org.assertj.core.api.Assertions.assertThat;

public class JSONReportTest {

    @Test
    public void whenJSON() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Gson gson = new GsonBuilder().create();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new JSONReport(store);
        StringBuilder expect = new StringBuilder()
                .append(String.format("[{\"name\":\"%s\",", worker.getName()))
                .append(String.format("\"hired\":%s,", gson.toJson(worker.getHired())))
                .append(String.format("\"fired\":%s,", gson.toJson(worker.getFired())))
                .append(String.format("\"salary\":%s}]", worker.getSalary()));
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}