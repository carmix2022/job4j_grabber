package ru.job4j.ood.srp.reporttype;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.*;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.report.ReportEngine;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class JsonReportTest {
    @Test
    public void whenJsonGenerated() throws JAXBException {
        MemStore store = new MemStore();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        JsonEmployeeSerializer jsonEmployeeSerializer = new JsonEmployeeSerializer1(parser);
        Employee worker1 = new Employee("Ivan", new GregorianCalendar(2017, 0, 25),
                new GregorianCalendar(2018, 0, 25), 30000);
        Employee worker2 = new Employee("Petr", new GregorianCalendar(2017, 0, 25),
                new GregorianCalendar(2018, 0, 25), 10000);
        Employee worker3 = new Employee("Alex", new GregorianCalendar(2017, 0, 25),
                new GregorianCalendar(2018, 0, 25), 20000);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        ReportType reportType = new JsonReport(store, parser, jsonEmployeeSerializer);
        Report engine = new ReportEngine(reportType);
        String expect = """
                [
                  {
                    "name": "Ivan",
                    "hired": "25:01:2017 00:00",
                    "fired": "25:01:2018 00:00",
                    "salary": 30000.0
                  },
                  {
                    "name": "Petr",
                    "hired": "25:01:2017 00:00",
                    "fired": "25:01:2018 00:00",
                    "salary": 10000.0
                  },
                  {
                    "name": "Alex",
                    "hired": "25:01:2017 00:00",
                    "fired": "25:01:2018 00:00",
                    "salary": 20000.0
                  }
                ]""";
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}
