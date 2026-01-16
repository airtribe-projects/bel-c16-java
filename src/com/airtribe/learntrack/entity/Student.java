package com.airtribe.learntrack.entity;

public class Student extends Person {
    private int studentId;

    public Student(int id, String firstName, String lastName) {
        super(firstName, lastName); // Now matches the new Person constructor
        this.studentId = id;
    }

    public int getStudentId() { return studentId; }
}