package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.security.KeyStore;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void satisfyAssert() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> sc = simpleConvert.toList("first", "fourteen", "fifteen", "four", "five");
        assertThat(sc).isNotEmpty()
                .allSatisfy(e -> {
                    assertThat(e).isLowerCase();
                    assertThat(e).contains("f");
                });
    }

    @Test
    void checkGroup() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> sc = simpleConvert.toSet("first", "fourteen", "fifteen", "four", "five");
        assertThat(sc).filteredOn(e -> e.length() != 4).doesNotContain("bob");
        assertThat(sc).filteredOnAssertions(e -> assertThat(e).contains("fi"))
                .hasSize(3)
                .allMatch(e -> e.length() >= 4);
    }

    @Test
    void checkOne() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> sc = simpleConvert.toList("first", "fourteen", "fifteen", "four", "five");
        assertThat(sc).first().isEqualTo("first");
        assertThat(sc).element(0).isNotNull()
                .isEqualTo("first");
        assertThat(sc).last().isNotNull()
                .isEqualTo("five");
        assertThat(sc).element(2).hasSameHashCodeAs("fifteen");
    }

    @Test
    void mapCheck() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "fourteen", "fifteen", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("first", "fifteen", "five")
                .doesNotContainKey("mouse")
                .hasSizeLessThan(10);
    }
}