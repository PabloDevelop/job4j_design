package ru.job4j.lsp.parking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MainPark implements Parking {
    private static final String ERR_MSG = "Slots are out. Try another parking.";
    private final int truckSlots;
    private final int carsSlots;
    private int countCarsInParking = 0;
    private int countTrucksInParking = 0;
    private final List<Transport> parkZoneForCars = new ArrayList<>();
    private final List<Transport> parkZoneForTrucks = new ArrayList<>();

    public MainPark(int truckSlots, int carsSlots) {
        this.truckSlots = truckSlots;
        this.carsSlots = carsSlots;
    }

    @Override
    public boolean park(Transport transport) {
        boolean rsl = false;
        if (transport.getSize() == 1) {
            try {
                rsl = parkCar(transport);
            } catch (IllegalArgumentException e) {
                System.out.println(ERR_MSG);
            }
        } else if (transport.getSize() > 1) {
            try {
                rsl = parkTruck(transport);
            } catch (IllegalArgumentException e) {
                System.out.println(ERR_MSG);
            }
        }
        return rsl;
    }

    private boolean parkCar(Transport transport) {
        boolean rsl;
        if (countCarsInParking < carsSlots) {
            rsl = parkZoneForCars.add(transport);
            countCarsInParking++;
        } else {
            throw new IllegalArgumentException();
        }
        return rsl;
    }

    private boolean parkTruck(Transport transport) {
        boolean rsl;
        if (countTrucksInParking < truckSlots) {
            rsl = parkZoneForTrucks.add(transport);
            countTrucksInParking++;
        } else if (countTrucksInParking == truckSlots
                && (carsSlots - countCarsInParking) >= transport.getSize()) {
            rsl = parkZoneForCars.add(transport);
            countCarsInParking += transport.getSize();
        } else {
            throw new IllegalArgumentException();
        }
        return rsl;
    }

    @Override
    public List<Transport> getAll() {
        List<List<Transport>> getAllList = new ArrayList<>();
        getAllList.add(parkZoneForTrucks);
        getAllList.add(parkZoneForCars);
        return getAllList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}