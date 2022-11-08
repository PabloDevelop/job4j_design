package ru.job4j.lsp.parking;

import java.util.Objects;

public class Car implements Transport {
    private static final int SIZE = 1;
    private final String name;
    private final String regNumber;

    public Car(String name, String regNumber) {
        this.name = name;
        this.regNumber = regNumber;
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    public String getName() {
        return name;
    }

    public String getRegNumber() {
        return regNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return Objects.equals(name, car.name)
                && Objects.equals(regNumber, car.regNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, regNumber);
    }

    @Override
    public String toString() {
        return "Car{"
                + "size=" + SIZE
                + ", name='" + name
                + '\''
                + ", regNumber='" + regNumber
                + '\''
                + '}';
    }
}