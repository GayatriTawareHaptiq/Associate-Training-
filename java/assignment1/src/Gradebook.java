import java.util.ArrayList;

public class Gradebook {
    private ArrayList<Student> studentList;

    public Gradebook() {
        this.studentList = new ArrayList<>();
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    /**
     * Adds a new student to the gradebook.
     * @param name The name of the student.
     */
    public void addStudent(String name) {
        studentList.add(new Student(name));
    }

    /**
     * Finds a student by name (case-insensitive).
     * @param name The name of the student to find.
     * @return The Student object if found, otherwise null.
     */
    public Student findStudent(String name) {
        for (Student student : studentList) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Generates a performance report as a formatted String.
     * @return The full report text.
     */
    public String generatePerformanceReport() {
        if (studentList.isEmpty()) {
            return "No data available to generate a report.";
        }

        // Use StringBuilder for efficient string concatenation
        StringBuilder report = new StringBuilder();

        report.append("\n--- Class Performance Report ---\n");
        report.append("==================================================\n");
        report.append("Student Name   | Average | Min | Max | Status\n");
        report.append("==================================================\n");

        for (Student student : studentList) {
            if (!student.hasGrades()) {
                report.append(String.format("%-14s | No Grades Yet\n", student.getName()));
            } else {
                double avg = student.getAverageGrade();
                int min = student.getMinGrade();
                int max = student.getMaxGrade();
                String status = (avg >= 50) ? "PASS" : "FAIL";

                report.append(String.format("%-14s | %-7.2f | %-3d | %-3d | %s\n",
                        student.getName(), avg, min, max, status));
            }
        }
        report.append("==================================================\n");

        return report.toString();
    }
}