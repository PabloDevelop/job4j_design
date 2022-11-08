package ru.job4j.lsp.parking;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import java.util.List;

public class MainParkTest {
    @Test
    public void when2CarsAnd1Truck() {
        Transport car1 = new Car("Volvo", "A123EE");
        Transport car2 = new Car("BMW", "E456ZZ");
        Transport truck = new Truck("MAN", "O999AS", 2);
        Parking parking = new MainPark(1, 2);
        parking.park(car1);
        parking.park(car2);
        parking.park(truck);
        assertThat(parking.getAll().containsAll(List.of(car1, car2, truck))).isTrue();
    }

    @Test
    public void whenOneTruckToRegularAndTruckSize2ToCars() {
        Transport truck1 = new Truck("MAN", "O999AS", 2);
        Transport truck2 = new Truck("VOLVO", "O8899AS", 2);
        Parking parking = new MainPark(1, 2);
        parking.park(truck1);
        parking.park(truck2);
        assertThat(parking.getAll().containsAll(List.of(truck1, truck2))).isTrue();
    }

    @Test
    public void whenTwoCarsOneTruckToRegularAndTruckSize3ToCars() {
        Transport car1 = new Car("Volvo", "A123EE");
        Transport car2 = new Car("BMW", "E456ZZ");
        Transport truck1 = new Truck("MAN", "O999AS", 2);
        Transport truck2 = new Truck("VOLVO", "O8899AS", 3);
        Parking parking = new MainPark(1, 5);
        parking.park(car1);
        parking.park(car2);
        parking.park(truck1);
        parking.park(truck2);
        assertThat(parking.getAll().containsAll(List.of(car1, car2, truck1, truck2))).isTrue();
    }

    @Test
    public void whenNoFreeSlotForCar() {
        Transport car1 = new Car("Volvo", "A123EE");
        Transport car2 = new Car("BMW", "E456ZZ");
        Transport car3 = new Car("Toyota", "S923ON");
        Parking parking = new MainPark(2, 2);
        parking.park(car1);
        parking.park(car2);
        assertThat(parking.park(car3)).isFalse();
    }

    @Test
    public void whenTwoCarsOneTruckToRegularAndTruckSize3ToCarsAndOneTruckDoNotPark() {
        Transport car1 = new Car("Volvo", "A123EE");
        Transport car2 = new Car("BMW", "E456ZZ");
        Transport truck1 = new Truck("MAN", "O999AS", 2);
        Transport truck2 = new Truck("VOLVO", "O8899AS", 3);
        Transport truck3 = new Truck("VOLVO", "O8899AS", 4);
        Parking parking = new MainPark(1, 5);
        parking.park(car1);
        parking.park(car2);
        parking.park(truck1);
        parking.park(truck2);
        assertThat(parking.park(truck3)).isFalse();
    }
}
