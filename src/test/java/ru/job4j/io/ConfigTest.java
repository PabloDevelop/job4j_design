package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./io.data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./io.data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenPairWithCommentAndEmptyString() {
        String path = "./io.data/pair_with_comment_and_empty_string.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenPairWithTwoEquals() {
        String path = "./io.data/pair_with_two_equals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect="));
        assertThat(config.value("name"), is("Petr Arsentev=I"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenExceptionWithoutEquals() {
        String path = "./io.data/without_equals.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenExceptionWithoutKey() {
        String path = "./io.data/without_key.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenExceptionWithoutKeyAndValue() {
        String path = "./io.data/without_key_and_value.properties";
        Config config = new Config(path);
        config.load();
    }
}