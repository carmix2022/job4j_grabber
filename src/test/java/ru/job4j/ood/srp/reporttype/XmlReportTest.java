package ru.job4j.ood.srp.reporttype;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.formatter.XmlEmployeeSerializer;
import ru.job4j.ood.srp.formatter.XmlEmployeeSerializer1;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.report.ReportEngine;
import ru.job4j.ood.srp.store.MemStore;

import javax.xml.bind.JAXBException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.*;

class XmlReportTest {
    @Test
    public void whenXmlGenerated() throws JAXBException {
        MemStore store = new MemStore();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        XmlEmployeeSerializer xmlEmployeeSerializer1 = new XmlEmployeeSerializer1(parser);
        Employee worker1 = new Employee("Ivan", new GregorianCalendar(2010, 0, 1),
                new GregorianCalendar(2018, 0, 25), 30000);
        Employee worker2 = new Employee("Petr", new GregorianCalendar(2010, 0, 1),
                new GregorianCalendar(2018, 0, 25), 10000);
        Employee worker3 = new Employee("Alex", new GregorianCalendar(2010, 0, 1),
                new GregorianCalendar(2018, 0, 25), 20000);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        ReportType reportType = new XmlReport(store, parser, xmlEmployeeSerializer1);
        Report engine = new ReportEngine(reportType);
        String expect = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>Ivan</name>
                        <hired>01:01:2010 00:00</hired>
                        <fired>25:01:2018 00:00</fired>
                        <salary>30000.0</salary>
                    </employee>
                    <employee>
                        <name>Petr</name>
                        <hired>01:01:2010 00:00</hired>
                        <fired>25:01:2018 00:00</fired>
                        <salary>10000.0</salary>
                    </employee>
                    <employee>
                        <name>Alex</name>
                        <hired>01:01:2010 00:00</hired>
                        <fired>25:01:2018 00:00</fired>
                        <salary>20000.0</salary>
                    </employee>
                </employees>
                """;
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}