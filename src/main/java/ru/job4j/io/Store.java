package ru.job4j.io;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "store")
@XmlAccessorType(XmlAccessType.FIELD)
public class Store {

    @XmlAttribute
    private boolean open;
    private Good good;
    private int price;

    @XmlElementWrapper
    @XmlElement(name = "stand_in_line")
    private String[] cashBoxes;

    public Store() {
    }

    public Store(boolean open, int price, Good good, String[] cashBoxes) {
        this.open = open;
        this.price = price;
        this.good = good;
        this.cashBoxes = cashBoxes;
    }

    @Override
    public String toString() {
        return "Store{"
                + "open=" + open
                + ", price=" + price
                + ", good=" + good
                + ", cashBoxes=" + Arrays.toString(cashBoxes)
                + '}';
    }
}