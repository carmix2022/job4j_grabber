package ru.job4j.ood.srp.reporttype;

import ru.job4j.ood.srp.formatter.*;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Employees;
import ru.job4j.ood.srp.store.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class XmlReport implements ReportType {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final JAXBContext context;
    private final Marshaller marshaller;


    public XmlReport(Store store, DateTimeParser<Calendar> dateTimeParser,
                     XmlEmployeeSerializer xmlEmployeeSerializer) throws JAXBException {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.context = JAXBContext.newInstance(Employees.class);
        this.marshaller = context.createMarshaller();
        this.marshaller.setAdapter(XmlEmployeeSerializer.class, xmlEmployeeSerializer);
    }

    @Override
    public StringBuilder formReport(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> list = store.findBy(filter);

        try {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (PropertyException e) {
            throw new RuntimeException(e);
        }
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(list), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return text.append(xml);
    }
}
