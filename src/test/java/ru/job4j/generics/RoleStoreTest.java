package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Baker"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Baker"));
    }

    @Test
    public void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Baker"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Baker"));
        store.add(new Role("1", "Blacksmith"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Baker"));
    }

    @Test
    public void whenReplaceThenUsernameIsMaxim() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Baker"));
        store.replace("1", new Role("1", "Blacksmith"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Blacksmith"));
    }

    @Test
    public void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.replace("10", new Role("10", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Petr"));
    }

    @Test
    public void whenDeleteUserThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteUserThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Petr"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Petr"));
    }
}