package ru.job4j.ood.srp.reporttype;

import ru.job4j.ood.srp.model.Employee;

import java.util.function.Predicate;

public interface ReportType {
    StringBuilder formReport(Predicate<Employee> filter);
}
