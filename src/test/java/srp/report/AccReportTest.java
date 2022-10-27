package srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.srp.currency.CurrencyConverter;
import ru.job4j.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.srp.formatter.DateTimeParser;
import ru.job4j.srp.formatter.ReportDateTimeParser;
import ru.job4j.srp.model.Employee;
import ru.job4j.srp.report.AccReport;
import ru.job4j.srp.report.Report;
import ru.job4j.srp.store.MemStore;

import javax.xml.bind.JAXBException;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.srp.currency.Currency.RUB;
import static ru.job4j.srp.currency.Currency.USD;
import java.util.Calendar;

public class AccReportTest {
    @Test
    public void whenHRReportSalarySorted() throws JAXBException {
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