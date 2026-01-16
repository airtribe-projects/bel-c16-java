package com.airtribe.learntrack.service;
import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    // Method name must be exactly this
    public void listAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student s : students) {
                System.out.println("ID: " + s.getStudentId() + " | Name: " + s.getFirstName() + " " + s.getLastName());
            }
        }
    }

    public void enrollInCourse(int studentId, String courseName) {
        System.out.println("Student ID " + studentId + " enrolled in " + courseName);
    }
}