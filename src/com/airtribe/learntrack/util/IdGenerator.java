package com.airtribe.learntrack.util;

public class IdGenerator {
    private static int counter = 1000; // Starting ID

    // Ensure the name is exactly generateId
    public static int generateId() {
        return ++counter;
    }
}