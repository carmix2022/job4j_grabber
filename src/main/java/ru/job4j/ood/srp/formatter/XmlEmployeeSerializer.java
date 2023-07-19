package ru.job4j.ood.srp.formatter;

import ru.job4j.ood.srp.model.Employee;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public abstract class XmlEmployeeSerializer extends XmlAdapter<XmlEmployeeSerializer1.AdaptedEmployee, Employee> {
}
