package com.example.gradeapp;// Import necessary tools for handling lists, user input, and writing to files.
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class GradeTrackerApp {


    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println(" Welcome to the Student Grade Tracker! ");
        System.out.println("=========================================");


        while (true) {
            printMenu();
            System.out.print("--------------------------\nEnter your choice: ");
            String input = scanner.nextLine();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\n[Error] Invalid input. Please enter a number.");
                continue;
            }


            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    enterGrades();
                    break;
                case 3:
                    displayReport();
                    break;
                case 4:
                    saveReportToFile();
                    break;
                case 5:
                    System.out.println("\nThank you for using the Grade Tracker. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("\n[Error] Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }


    private static void printMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Add a new Student");
        System.out.println("2. Enter Grades for a Student");
        System.out.println("3. Display Class Performance Report");
        System.out.println("4. Save Report to File");
        System.out.println("5. Exit");
    }


    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();

        // Check if the name is empty.
        if (name.trim().isEmpty()) {
            System.out.println("[Error] Student name cannot be empty.");
            return;
        }

        // Create a new Student object and add it to our list.
        studentList.add(new Student(name));
        System.out.println("Student '" + name + "' added successfully!");
    }


    private static void enterGrades() {
        System.out.println("\n--- Enter Student Grades ---");
        if (studentList.isEmpty()) {
            System.out.println("No students have been added yet. Please add a student first.");
            return;
        }

        System.out.print("Enter the name of the student to add grades for: ");
        String name = scanner.nextLine();

       
        Student foundStudent = null;
        for (Student student : studentList) {

            if (student.name.equalsIgnoreCase(name)) {
                foundStudent = student;
                break;
            }
        }


        if (foundStudent == null) {
            System.out.println("[Error] Student '" + name + "' not found.");
            return;
        }

        // Now get the grades.
        System.out.print("Enter a grade (0-100) and press Enter. Type 'done' when finished: ");
        while (true) {
            String gradeInput = scanner.nextLine();
            if (gradeInput.equalsIgnoreCase("done")) {
                break;
            }

            try {
                int grade = Integer.parseInt(gradeInput);
                if (grade >= 0 && grade <= 100) {
                    foundStudent.addGrade(grade);
                    System.out.println("Grade " + grade + " added. Enter next grade or type 'done'.");
                } else {
                    System.out.println("[Error] Invalid grade. Please enter a number between 0 and 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[Error] Invalid input. Please enter a number or 'done'.");
            }
        }
        System.out.println("Grades updated for " + foundStudent.name);
    }


    private static void displayReport() {
        System.out.println("\n--- Class Performance Report ---");
        if (studentList.isEmpty()) {
            System.out.println("No data available to generate a report.");
            return;
        }

        System.out.println("==================================================");
        System.out.println("Student Name   | Average | Min | Max | Status");
        System.out.println("==================================================");


        for (Student student : studentList) {
            if (student.grades.isEmpty()) {
                System.out.println(student.name + " | No Grades Yet");
            } else {
                double avg = calculateAverage(student.grades);
                int min = findMinGrade(student.grades);
                int max = findMaxGrade(student.grades);
                String status = (avg >= 50) ? "PASS" : "FAIL"; // A quick way to check for pass/fail.

                // Print the student's details.
                System.out.printf("%-14s | %-7.2f | %-3d | %-3d | %s\n", student.name, avg, min, max, status);
            }
        }
        System.out.println("==================================================");
    }

    //Saves the performance report to a text file.
    private static void saveReportToFile() {
        System.out.println("\n--- Save Report to File ---");
        if (studentList.isEmpty()) {
            System.out.println("No data available to save.");
            return;
        }

        // We build the entire report as a single string of text.
        String reportContent = "--- Class Performance Report ---\n";
        reportContent += "==================================================\n";
        reportContent += "Student Name   | Average | Min | Max | Status\n";
        reportContent += "==================================================\n";

        for (Student student : studentList) {
            if (student.grades.isEmpty()) {
                reportContent += student.name + " | No Grades Yet\n";
            } else {
                double avg = calculateAverage(student.grades);
                int min = findMinGrade(student.grades);
                int max = findMaxGrade(student.grades);
                String status = (avg >= 50) ? "PASS" : "FAIL";
                reportContent += String.format("%-14s | %-7.2f | %-3d | %-3d | %s\n", student.name, avg, min, max, status);
            }
        }
        reportContent += "==================================================\n";

        // Now, we write this string to a file.
        try {
            FileWriter writer = new FileWriter("student_report.txt");
            writer.write(reportContent);
            writer.close(); // Important: Always close the writer when you're done.
            System.out.println("Report successfully saved to 'student_report.txt'");
        } catch (IOException e) {
            System.out.println("[Error] An error occurred while writing the file.");
        }
    }

    // --- Helper Methods for Calculations ---


    public static double calculateAverage(ArrayList<Integer> grades) {
        if (grades.isEmpty()) return 0;

        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }


    public static int findMaxGrade(ArrayList<Integer> grades) {
        if (grades.isEmpty()) return 0;

        int max = grades.get(0);
        for (int grade : grades) {
            if (grade > max) {
                max = grade;
            }
        }
        return max;
    }


    public static int findMinGrade(ArrayList<Integer> grades) {
        if (grades.isEmpty()) return 0;

        int min = grades.get(0);
        for (int grade : grades) {
            if (grade < min) {
                min = grade;
            }
        }
        return min;
    }
}
