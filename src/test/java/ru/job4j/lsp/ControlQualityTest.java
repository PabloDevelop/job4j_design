package ru.job4j.lsp;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import ru.job4j.lsp.food.Cheese;
import ru.job4j.lsp.food.Cucumber;
import ru.job4j.lsp.food.Food;
import ru.job4j.lsp.food.Potato;
import ru.job4j.lsp.store.Shop;
import ru.job4j.lsp.store.Store;
import ru.job4j.lsp.store.Trash;
import ru.job4j.lsp.store.Warehouse;
import java.time.LocalDateTime;
import java.util.List;

public class ControlQualityTest {
    Store shop = new Shop();
    Store warehouse = new Warehouse();
    Store trash = new Trash();
    ControlQuality control = new ControlQuality(List.of(shop, warehouse, trash));


    @Test
    public void whenToWarehouse() {
        Food bread = new Food("White bread", LocalDateTime.now().plusDays(10),
                LocalDateTime.now().minusDays(2), 100, 25);
        control.sortFood(bread);
        assertThat(warehouse.showAllFood().contains(bread)).isTrue();
    }

    @Test
    public void whenToShop() {
        Food bread = new Food("White bread", LocalDateTime.now().plusDays(10),
                LocalDateTime.now().minusDays(10), 100, 25);
        control.sortFood(bread);
        assertThat(shop.showAllFood().contains(bread)).isTrue();
    }

    @Test
    public void whenToShopWithDiscount() {
        Food bread = new Food("White bread", LocalDateTime.now().plusDays(2),
                LocalDateTime.now().minusDays(10), 100, 25);
        control.sortFood(bread);
        assertThat(shop.showAllFood().get(0).getPrice()).isEqualTo(75);
    }

    @Test
    public void whenToTrash() {
        Food bread = new Food("White bread", LocalDateTime.now().plusDays(1),
                LocalDateTime.now().minusDays(10), 100, 25);
        control.sortFood(bread);
        assertThat(trash.showAllFood().contains(bread)).isTrue();
    }

    @Test
    public void whenAllControl() {
        Food potato = new Potato("Potato", LocalDateTime.now().plusDays(10),
                LocalDateTime.now().minusDays(2), 100, 25);
        Food cucumber = new Cucumber("Cucumber", LocalDateTime.now().plusDays(10),
                LocalDateTime.now().minusDays(10), 100, 25);
        Food tomato = new Food("Tomato", LocalDateTime.now().plusDays(2),
                LocalDateTime.now().minusDays(10), 100, 25);
        Food cheese = new Cheese("Cheese", LocalDateTime.now().plusDays(1),
                LocalDateTime.now().minusDays(10), 100, 25);
        control.sortFood(potato);
        control.sortFood(cucumber);
        control.sortFood(tomato);
        control.sortFood(cheese);
        assertThat(warehouse.showAllFood().contains(potato)).isTrue();
        assertThat(shop.showAllFood().containsAll(List.of(cucumber, tomato))).isTrue();
        assertThat(trash.showAllFood().contains(cheese)).isTrue();
    }
}