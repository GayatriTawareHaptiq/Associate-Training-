import java.util.ArrayList;
import java.util.Collections;

public class Student {
    private String name;
    private ArrayList<Integer> grades;

    /**
     * Constructor to create a new Student.
     */
    public Student(String name) {
        this.name = name;
        this.grades = new ArrayList<>();
    }

    // --- Getters ---
    public String getName() {
        return name;
    }

    public ArrayList<Integer> getGrades() {
        return grades;
    }

    /**
     * Adds a single grade to the student's list of grades.

     */
    public void addGrade(int grade) {
        this.grades.add(grade);
    }

    public boolean hasGrades() {
        return !grades.isEmpty();
    }

    // --- Calculation Methods ---

    public double getAverageGrade() {
        if (grades.isEmpty()) return 0.0;
        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public int getMaxGrade() {
        if (grades.isEmpty()) return 0;
        return Collections.max(grades);
    }

    public int getMinGrade() {
        if (grades.isEmpty()) return 0;
        return Collections.min(grades);
    }
}