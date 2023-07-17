package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.reporttype.BuhReport;
import ru.job4j.ood.srp.reporttype.HRReport;
import ru.job4j.ood.srp.reporttype.ProgReport;
import ru.job4j.ood.srp.reporttype.ReportType;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

public class HRReportTest {

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Employee worker1 = new Employee("Ivan", new GregorianCalendar(2017, 0, 25),
                new GregorianCalendar(2018, 0, 25), 30000);
        Employee worker2 = new Employee("Petr", new GregorianCalendar(2017, 0, 25),
                new GregorianCalendar(2018, 0, 25), 10000);
        Employee worker3 = new Employee("Alex", new GregorianCalendar(2017, 0, 25),
                new GregorianCalendar(2018, 0, 25), 20000);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        ReportType reportType = new HRReport(store);
        Report engine = new ReportEngine(reportType);
        String expect = "Name; Salary;" + System.lineSeparator() + "Petr 10000.0" + System.lineSeparator()
                + "Alex 20000.0" + System.lineSeparator() + "Ivan 30000.0" + System.lineSeparator();
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}