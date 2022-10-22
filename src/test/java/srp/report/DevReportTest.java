package srp.report;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import srp.output.CSVOutput;
import srp.output.Output;
import srp.formatter.DateTimeParser;
import srp.formatter.ReportDateTimeParser;
import srp.model.Employee;
import srp.store.MemStore;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;
import static org.assertj.core.api.Assertions.assertThat;

public class DevReportTest {

    @Test
    public void whenDevReport(@TempDir Path tempDir) throws IOException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Path file = tempDir.resolve("DevReport.csv");
        Output output = new CSVOutput(file.toString());
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Vasiliy", now, now, 150);
        Employee worker2 = new Employee("Oleg", now, now, 120);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker1);
        store.add(worker2);
        Report engine = new ReportEngine(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(parser.parse(worker.getHired())).append(";")
                .append(parser.parse(worker.getFired())).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(parser.parse(worker1.getHired())).append(";")
                .append(parser.parse(worker1.getFired())).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(parser.parse(worker2.getHired())).append(";")
                .append(parser.parse(worker2.getFired())).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator());
        output.output(engine.generate(em -> true));
        assertThat(Files.readString(file.toAbsolutePath())).isEqualTo(expect.toString());
    }
}