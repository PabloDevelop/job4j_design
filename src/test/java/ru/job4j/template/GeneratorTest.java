package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.HashMap;

@Disabled
class GeneratorTest {

    @Disabled
    @Test
    public void whenReturnSuccess() {
        Generator generator = new Generator();
        String template = "I am a ${name}, Who are ${subject}? ";
        HashMap<String, String> newMap = new HashMap<>();
        newMap.put("name", "Petr Arsentev");
        newMap.put("subject", "you");
        String rsl = generator.produce(template, newMap);
        String expected = "I am a Petr Arsentev, Who are you? ";
        assertThat(rsl).isEqualTo(expected);
    }

    @Disabled
    @Test
    public void whenKeysIsNotInMap() {
        Generator generator = new Generator();
        String template = "I am a ${name}, Who are ${subject}? ";
        HashMap<String, String> newMap = new HashMap<>();
        newMap.put("name", "Petr Arsentev");
        newMap.put("second name", "Arsentev");
        newMap.put("subject", "you");
        assertThrows(IllegalArgumentException.class,
                () -> generator.produce(template, newMap));
    }

    @Disabled
    @Test
    public void whenKeysIsNotInTemplate() {
        Generator generator = new Generator();
        String template = "I ${be_form} a ${name}, Who are ${subject}? ";
        HashMap<String, String> newMap = new HashMap<>();
        newMap.put("name", "Petr Arsentev");
        newMap.put("subject", "you");
        assertThrows(IllegalArgumentException.class,
                () -> generator.produce(template, newMap));
    }
}