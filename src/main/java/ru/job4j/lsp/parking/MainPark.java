package ru.job4j.lsp.parking;

import static ru.job4j.lsp.parking.Car.*;
import java.util.ArrayList;
import java.util.List;

public class MainPark implements Parking {
    private final int truckSlots;
    private final int carSlots;
    private int countCarsInParking = 0;
    private int countTrucksInParking = 0;
    private final List<Transport> parkZoneForCars;
    private final List<Transport> parkZoneForTrucks;

    public MainPark(int truckSlots, int carsSlots) {
        this.truckSlots = truckSlots;
        this.carSlots = carsSlots;
        this.parkZoneForCars = new ArrayList<>(carsSlots);
        this.parkZoneForTrucks = new ArrayList<>(truckSlots);
    }

    @Override
    public boolean park(Transport transport) {
        boolean rsl = false;
        if (transport.getSize() == SIZE) {
            rsl = parkCar(transport);
        } else if (transport.getSize() > SIZE) {
            rsl = parkTruck(transport);
        }
        return rsl;
    }

    private boolean parkCar(Transport transport) {
        boolean rsl = false;
        if (countCarsInParking < carSlots) {
            rsl = parkZoneForCars.add(transport);
            countCarsInParking++;
        }
        return rsl;
    }

    private boolean parkTruck(Transport transport) {
        boolean rsl = false;
        if (countTrucksInParking < truckSlots) {
            rsl = parkZoneForTrucks.add(transport);
            countTrucksInParking++;
        } else if (countTrucksInParking == truckSlots
                && (carSlots - countCarsInParking) >= transport.getSize()) {
            rsl = parkZoneForCars.add(transport);
            countCarsInParking += transport.getSize();
        }
        return rsl;
    }

    @Override
    public List<Transport> getAll() {
        List<Transport> getAllList = new ArrayList<>(parkZoneForTrucks);
        getAllList.addAll(parkZoneForCars);
        return getAllList;
    }
}