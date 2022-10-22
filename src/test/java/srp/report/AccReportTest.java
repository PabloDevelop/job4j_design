package srp.report;

import org.junit.jupiter.api.Test;
import srp.currency.CurrencyConverter;
import srp.currency.InMemoryCurrencyConverter;
import srp.formatter.DateTimeParser;
import srp.formatter.ReportDateTimeParser;
import srp.model.Employee;
import srp.store.MemStore;
import static org.assertj.core.api.Assertions.*;
import static srp.currency.Currency.RUB;
import static srp.currency.Currency.USD;
import java.util.Calendar;

public class AccReportTest {
    @Test
    public void whenHRReportSalarySorted() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Vasiliy", now, now, 150);
        Employee worker2 = new Employee("Oleg", now, now, 120);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker1);
        store.add(worker2);
        Report engine = new AccReport(store, parser, converter);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(parser.parse(worker.getHired())).append(";")
                .append(parser.parse(worker.getFired())).append(";")
                .append(converter.convert(RUB, worker.getSalary(), USD)).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(parser.parse(worker1.getHired())).append(";")
                .append(parser.parse(worker1.getFired())).append(";")
                .append(converter.convert(RUB, worker1.getSalary(), USD)).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(parser.parse(worker2.getHired())).append(";")
                .append(parser.parse(worker2.getFired())).append(";")
                .append(converter.convert(RUB, worker2.getSalary(), USD)).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}