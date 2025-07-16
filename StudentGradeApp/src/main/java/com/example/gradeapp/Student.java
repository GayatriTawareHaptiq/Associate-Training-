package com.example.gradeapp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    ArrayList<Integer> grades;

    // Constructor to create a new Student.
    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }


    public void addGrade(int grade) {
        this.grades.add(grade);
    }
}