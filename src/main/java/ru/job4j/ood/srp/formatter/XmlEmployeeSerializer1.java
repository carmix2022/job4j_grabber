package ru.job4j.ood.srp.formatter;

import ru.job4j.ood.srp.model.Employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Calendar;

public class XmlEmployeeSerializer1 extends XmlEmployeeSerializer {

    private final DateTimeParser<Calendar> dateTimeParser;
    public XmlEmployeeSerializer1(DateTimeParser<Calendar> dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public Employee unmarshal(AdaptedEmployee adaptedEmployee) throws Exception {
        return null;
    }

    @Override
    public AdaptedEmployee marshal(Employee employee) throws Exception {
        AdaptedEmployee adaptedEmployee = new AdaptedEmployee();
        adaptedEmployee.name = employee.getName();
        adaptedEmployee.hired = dateTimeParser.parse(employee.getHired());
        adaptedEmployee.fired = dateTimeParser.parse(employee.getFired());
        adaptedEmployee.salary = employee.getSalary();
        return adaptedEmployee;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AdaptedEmployee {
        private String name;
        private String hired;
        private String fired;
        private double salary;
    }
}
