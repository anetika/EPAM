package edu.epam.shapes.util;

public class IdGenerator {
    private static long id = 0;
    public static long generateId(){
        return ++id;
    }
}
