package ru.job4j.lsp.parking;

import java.util.Objects;

public class Car implements Transport {
    private final int size;
    private final String name;
    private final String regNumber;

    public Car(String name, String regNumber, int size) {
        this.name = name;
        this.regNumber = regNumber;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
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
        return size == car.size
                && Objects.equals(name, car.name)
                && Objects.equals(regNumber, car.regNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name, regNumber);
    }

    @Override
    public String toString() {
        return "Car{"
                + "size=" + size
                + ", name='" + name
                + '\''
                + ", regNumber='" + regNumber
                + '\''
                + '}';
    }
}