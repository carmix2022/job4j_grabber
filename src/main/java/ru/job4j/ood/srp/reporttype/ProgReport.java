package ru.job4j.ood.srp.reporttype;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class ProgReport implements ReportType {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final String delimeter;

    public ProgReport(Store store, DateTimeParser<Calendar> dateTimeParser, String delimeter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.delimeter = delimeter;
    }

    @Override
    public StringBuilder formReport(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(new StringJoiner(delimeter, "", delimeter).add("Name")
                .add("Hired")
                .add("Fired")
                .add("Salary"))
                .append(System.lineSeparator());
        List<Employee> list = store.findBy(filter);
        for (Employee employee : list) {
            text.append(employee.getName()).append(delimeter)
                    .append(dateTimeParser.parse(employee.getHired())).append(delimeter)
                    .append(dateTimeParser.parse(employee.getFired())).append(delimeter)
                    .append(employee.getSalary()).append(delimeter)
                    .append(System.lineSeparator());
        }
        return text;
    }
}
