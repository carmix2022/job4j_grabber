package ru.job4j.ood.srp.reporttype;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.JsonEmployeeSerializer;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class JsonReport implements ReportType {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Gson gson;

    public JsonReport(Store store, DateTimeParser<Calendar> dateTimeParser, JsonEmployeeSerializer jsonEmployeeSerializer) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(Employee.class, jsonEmployeeSerializer).create();
    }

    @Override
    public StringBuilder formReport(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> list = store.findBy(filter);
        return text.append(gson.toJson(list));
    }
}
