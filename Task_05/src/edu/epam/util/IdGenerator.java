package edu.epam.util;

public class IdGenerator {
    private static long id = 0;

    public static long generateId() {
        return ++id;
    }
}
