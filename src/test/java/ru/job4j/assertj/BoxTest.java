package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .isNotEqualTo("Cube");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .isNotEqualTo("Sphere");
    }

    @Test
    void isNumberOfVertices0() {
        Box box = new Box(0, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isZero()
                .isLessThan(1)
                .isEqualTo(0);
    }

    @Test
    void isNumberOfVertices4() {
        Box box = new Box(4, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(1)
                .isLessThan(5)
                .isEqualTo(4);
    }

    @Test
    void isExistIsTrue() {
        Box box = new Box(4, 10);
        boolean result = box.isExist();
        assertThat(result).isTrue()
                .isEqualTo(true);
    }

    @Test
    void isExistIsFalse() {
        Box box = new Box(3, 10);
        boolean result = box.isExist();
        assertThat(result).isFalse()
                .isEqualTo(false);
    }

    @Test
    void areaSphereIs1256() {
        Box box = new Box(0, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(1256.64d, withPrecision(0.008d))
                .isGreaterThan(1256.50d);
    }

    @Test
    void areaCubeIs864() {
        Box box = new Box(8, 12);
        double result = box.getArea();
        assertThat(result).isEqualTo(864.00d, withPrecision(0.001d))
                .isLessThan(864.10d)
                .isBetween(863.00d, 875.00d);
    }
}