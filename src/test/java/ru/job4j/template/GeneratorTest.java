package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.HashMap;

@Disabled
class GeneratorTest {

    @Test
    public void whenReturnSuccess() {
        Generator generator = new GeneratorUsable();
        String template = "I am a ${name}, Who are ${subject}? ";
        HashMap<String, String> newMap = new HashMap<>();
        newMap.put("name", "Petr Arsentev");
        newMap.put("subject", "you");
        String rsl = generator.produce(template, newMap);
        assertThat(rsl).isEqualTo("I am a Petr Arsentev, Who are you? ");
    }

    @Test
    public void whenInMapAreMoreKeys() {
        Generator generator = new GeneratorUsable();
        String template = "I am a ${name}, Who are ${subject}? ";
        HashMap<String, String> newMap = new HashMap<>();
        newMap.put("name", "Petr Arsentev");
        newMap.put("second name", "Arsentev");
        newMap.put("subject", "you");
        assertThrows(IllegalArgumentException.class,
                () -> generator.produce(template, newMap));
    }

    @Test
    public void whenKeysIsNotInTemplate() {
        Generator generator = new GeneratorUsable();
        String template = "I ${be_form} a ${name}, Who are ${subject}? ";
        HashMap<String, String> newMap = new HashMap<>();
        newMap.put("name", "Petr Arsentev");
        newMap.put("subject", "you");
        assertThrows(IllegalArgumentException.class,
                () -> generator.produce(template, newMap));
    }
}