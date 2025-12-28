package com.airtribe.learntrack.entity;

public class Person {
    private String firstName;
    private String lastName;

    // Change this to only take two Strings
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
}