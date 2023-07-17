package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.reporttype.ReportType;

import java.util.function.Predicate;

public class ReportEngine implements Report {

    private final ReportType reportType;

    public ReportEngine(ReportType reportType) {
        this.reportType = reportType;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return reportType.formReport(filter).toString();
    }
}