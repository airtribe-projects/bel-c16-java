package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Welcome to LearnTrack Student Management System ===");

        while (running) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter First Name: ");
                    String fName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lName = scanner.nextLine();
                    
                    int id = IdGenerator.generateId();
                    Student newStudent = new Student(id, fName, lName);
                    service.addStudent(newStudent);
                    System.out.println("Student added successfully with ID: " + id);
                    break;

                case 2:
                    System.out.println("\n--- Student List ---");
                    service.listAllStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    int sId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Course Name: ");
                    String cName = scanner.nextLine();
                    
                    // Demonstrating Course entity and Enrollment logic
                    service.enrollInCourse(sId, cName);
                    break;

                case 4:
                    running = false;
                    System.out.println("Exiting System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}