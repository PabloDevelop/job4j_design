package srp.report;

import srp.formatter.DateTimeParser;
import srp.model.Employee;
import srp.store.Store;
import java.util.Calendar;
import java.util.Comparator;
import java.util.function.Predicate;

public class HRReport implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public HRReport(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        store.findBy(filter)
                .stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                .forEach(employee -> {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        });
        return text.toString();
    }
}