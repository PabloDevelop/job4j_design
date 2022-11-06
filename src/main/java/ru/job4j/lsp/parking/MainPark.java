package ru.job4j.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class MainPark implements Parking {
    private final List<Transport> parkZone;

    public MainPark() {
        this.parkZone = new ArrayList<>();
    }

    @Override
    public boolean park(Transport transport) {
        return false;
    }

    @Override
    public List<Transport> getAll() {
        return List.copyOf(parkZone);
    }
}