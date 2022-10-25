package srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.srp.model.Employee;
import ru.job4j.srp.report.HRReport;
import ru.job4j.srp.report.Report;
import ru.job4j.srp.store.MemStore;
import static org.assertj.core.api.Assertions.*;
import java.util.Calendar;
import java.util.Comparator;

public class HRReportTest {
    @Test
    public void whenHRReportSalarySorted() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker1 = new Employee("Vasiliy", now, now, 150);
        Employee worker2 = new Employee("Oleg", now, now, 120);
        store.add(worker);
        store.add(worker1);
        store.add(worker2);
        Report engine = new HRReport(store, Comparator.comparingDouble(Employee::getSalary).reversed());
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}