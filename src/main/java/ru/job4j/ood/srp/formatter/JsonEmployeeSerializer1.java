package ru.job4j.ood.srp.formatter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.job4j.ood.srp.model.Employee;

import java.lang.reflect.Type;
import java.util.Calendar;

public class JsonEmployeeSerializer1 implements JsonEmployeeSerializer, JsonSerializer<Employee> {
    private final DateTimeParser<Calendar> dateTimeParser;

    public JsonEmployeeSerializer1(DateTimeParser<Calendar> dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }


    @Override
    public JsonElement serialize(Employee employee, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("name", employee.getName());
        object.addProperty("hired", dateTimeParser.parse(employee.getHired()));
        object.addProperty("fired", dateTimeParser.parse(employee.getFired()));
        object.addProperty("salary", employee.getSalary());
        return object;
    }
}
