package ru.job4j.io;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "good")
public class Good {

    @XmlAttribute
    private String title;

    public Good() {

    }

    @Override
    public String toString() {
        return "Good{"
                + "title='" + title + '\''
                + '}';
    }

    public Good(String title) {
        this.title = title;
    }
}