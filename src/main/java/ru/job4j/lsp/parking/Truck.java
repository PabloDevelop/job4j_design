package ru.job4j.lsp.parking;

import java.util.Objects;

public class Truck implements Transport {
    private final int size;
    private final String name;
    private final String regNumber;

    public Truck(String name, String regNumber, int size) {
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
        Truck truck = (Truck) o;
        return size == truck.size
                && Objects.equals(name, truck.name)
                && Objects.equals(regNumber, truck.regNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, name, regNumber);
    }

    @Override
    public String toString() {
        return "Truck{"
                + "size=" + size
                + ", name='" + name
                + '\''
                + ", regNumber='" + regNumber
                + '\''
                + '}';
    }
}