package ru.job4j.srp.report;

import org.junit.Test;
import ru.job4j.srp.formatter.DateTimeParser;
import ru.job4j.srp.formatter.ReportDateTimeParser;
import ru.job4j.srp.model.Employee;
import ru.job4j.srp.store.MemStore;
import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static org.assertj.core.api.Assertions.assertThat;

public class XMLReportTest {

    @Test
    public void whenXML() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        store.add(worker);
        Report engine = new XMLReport(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append(String.format("%n%s%n", "<Store>"))
                .append(String.format("    %s%n", "<employees>"))
                .append(String.format("        %s%n", "<employee>"))
                .append(String.format("            <name>%s</name>%n", worker.getName()))
                .append(String.format("            <hired>%s</hired>%n", dateFormat.format(
                        worker.getHired().getTime())))
                .append(String.format("            <fired>%s</fired>%n", dateFormat.format(
                        worker.getFired().getTime())))
                .append(String.format("            <salary>%s</salary>%n", worker.getSalary()))
                .append(String.format("        %s%n", "</employee>"))
                .append(String.format("    %s%n", "</employees>"))
                .append(String.format("%s%n", "</Store>"));
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}