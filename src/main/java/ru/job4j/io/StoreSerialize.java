package ru.job4j.io;

import javax.xml.bind.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class StoreSerialize {
    public static void main(String[] args) {
        Store store = new Store(true, 45, new Good("Bread"),
                new String[]{"3 people", "4 people"});
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Store.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        /* Создаем сериализатор */
        Marshaller marshaller = null;
        try {
            marshaller = context.createMarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        /* Указываем, что нам нужно форматирование */
        try {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (PropertyException e) {
            e.printStackTrace();
        }
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(store, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            Store result = null;
            try {
                result = (Store) unmarshaller.unmarshal(reader);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            System.out.println(result);
        }
    }
}
