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

public class ProgReportTest {
    @Test
    public void whenProgGenerated() {
        MemStore store = new MemStore();
        Employee worker = new Employee("Ivan", new GregorianCalendar(2017, 0, 25),
                new GregorianCalendar(2018, 0, 25), 10000);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        ReportType reportType = new ProgReport(store, parser, ";");
        Report engine = new ReportEngine(reportType);
        String expect = "Name;Hired;Fired;Salary;" + System.lineSeparator()
                + "Ivan;25:01:2017 00:00;25:01:2018 00:00;10000.0;" + System.lineSeparator();
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}