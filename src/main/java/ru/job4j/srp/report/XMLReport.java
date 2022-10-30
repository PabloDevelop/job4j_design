package ru.job4j.srp.report;

import ru.job4j.srp.model.Employee;
import ru.job4j.srp.model.Employees;
import ru.job4j.srp.store.Store;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

public class XMLReport implements Report {

    private final Store store;
    private final JAXBContext context;
    private final Marshaller marshaller;

    public XMLReport(Store store) throws JAXBException {
        this.store = store;
        context = JAXBContext.newInstance(Employees.class);
        marshaller = context.createMarshaller();
    }

    @Override
    public String generate(Predicate<Employee> filter)  {
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new Employees(store.findBy(filter)), writer);
            xml = writer.getBuffer().toString();
        } catch (JAXBException | IOException e) {
            throw new IllegalArgumentException();
        }
        return xml;
    }
}