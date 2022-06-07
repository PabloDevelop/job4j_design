package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleMapTest {
    @Test
    public void whenGetIsValue() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Vasya", 40);
        map.put("Petya", 50);
        Assert.assertThat(map.get("Vasya"), is(40));
        Assert.assertThat(map.get("Petya"), is(50));
    }

    @Test
    public void whenGetIsNull() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        Assert.assertNull(map.get("Vasya"));
    }

    @Test
    public void whenPut() {
        SimpleMap<Integer, String > map = new SimpleMap<>();
        map.put(1, "Vasya");
        map.put(5, "Petya");
        Assert.assertThat(map.get(1), is("Vasya"));
        Assert.assertThat(map.get(5), is("Petya"));
    }

    @Test
    public void whenPutIsFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Vasya");
        Assert.assertFalse(map.put(1, "Petya"));

    }

    @Test
    public void whenRemove() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Vasya");
        map.remove(1);
        Assert.assertNull(map.get(1));
    }

    @Test
    public void whenRemoveIsFalse() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Assert.assertFalse(map.remove(1));
    }

    @Test
    public void whenIteratorHasNext() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> it = map.iterator();
        assertThat(it.hasNext(), is(true));
    }


    @Test(expected = NullPointerException.class)
    public void whenNextFromEmpty() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> it = map.iterator();
        it.next();
    }
}