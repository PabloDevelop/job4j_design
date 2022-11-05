package ru.job4j.lsp.parking;

import java.util.List;

public interface Parking {
    boolean park(Transport transport);

    List<Transport> getAll();
}