package srp.report;

import srp.formatter.DateTimeParser;
import srp.model.Employee;
import srp.store.Store;
import java.util.Calendar;
import java.util.Comparator;
import java.util.function.Predicate;

public class HRReport implements Report {

    private final Comparator<Employee> comparator;
    private final Store store;

    public HRReport(Store store, Comparator comparator) {
        this.store = store;
        this.comparator = comparator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        store.findBy(filter)
                .stream()
                .sorted(comparator)
                .forEach(employee -> {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        });
        return text.toString();
    }
}