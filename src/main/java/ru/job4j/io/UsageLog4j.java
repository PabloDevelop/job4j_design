package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        int age = 33;
        double growth = 1.82;
        float footLength = 0.32f;
        long hairCount = 1_000_000_000L;
        byte fingersCount = 10;
        boolean human = true;
        char sex = 'M';
        short salary = 30_000;
        LOG.info("User info age : {}, foot length : {}, hair count : {}, fingers count : {}, race : {}, sex : {}, salary : {}", age, growth,
                footLength, hairCount, fingersCount, human, sex, salary);
    }
}