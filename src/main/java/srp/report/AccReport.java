package srp.report;

import srp.currency.CurrencyConverter;
import srp.currency.InMemoryCurrencyConverter;
import srp.formatter.DateTimeParser;
import srp.model.Employee;
import srp.store.Store;
import java.util.Calendar;
import java.util.function.Predicate;

import static srp.currency.Currency.RUB;
import static srp.currency.Currency.USD;

public class AccReport implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    private final CurrencyConverter converter;

    public AccReport(Store store, DateTimeParser<Calendar> dateTimeParser, CurrencyConverter converter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.converter = converter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(dateTimeParser.parse(employee.getHired())).append(";")
                    .append(dateTimeParser.parse(employee.getFired())).append(";")
                    .append(converter.convert(RUB, employee.getSalary(), USD)).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}