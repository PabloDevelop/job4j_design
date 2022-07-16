package ru.job4j.io;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "good")
public class Good {

    @XmlAttribute
    private String title;

    public Good() {

    }

    public Good(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Good{"
                + "title='" + title + '\''
                + '}';
    }
}