import java.util.Scanner;

public class AppUI {
    private Scanner scanner;
    private Gradebook gradebook;

    public AppUI(Gradebook gradebook) {
        this.scanner = new Scanner(System.in);
        this.gradebook = gradebook;
    }

    /**
     * Starts the main application loop.
     */
    public void start() {
        OutputManager.log("=========================================");
        OutputManager.log(" Welcome to the Student Grade Tracker! ");
        OutputManager.log("=========================================");

        while (true) {
            printMenu();
            OutputManager.log("--------------------------");
            System.out.print("Enter your choice: "); // Use System.out for prompt to avoid logging it
            String input = scanner.nextLine();
            OutputManager.log("> User choice: " + input); // Log the user's input

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                OutputManager.log("\n[Error] Invalid input. Please enter a number.");
                continue;
            }

            if (choice == 5) {
                OutputManager.log("\nThank you for using the Grade Tracker. Goodbye!");
                break; // Exit the loop
            }

            handleMenuChoice(choice);
        }
        scanner.close();
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                promptAddStudent();
                break;
            case 2:
                promptEnterGrades();
                break;
            case 3:
                displayReport();
                break;
            case 4:
                // This option is now implicitly handled by the OutputManager
                OutputManager.log("\n[Info] All output is automatically saved to 'grade_tracker_log.txt'.");
                break;
            default:
                OutputManager.log("\n[Error] Invalid choice. Please enter a number between 1 and 5.");
        }
    }

    private void printMenu() {
        OutputManager.log("\n--- MENU ---");
        OutputManager.log("1. Add a new Student");
        OutputManager.log("2. Enter Grades for a Student");
        OutputManager.log("3. Display Class Performance Report");
        OutputManager.log("4. (All output is now auto-saved)");
        OutputManager.log("5. Exit");
    }

    private void promptAddStudent() {
        OutputManager.log("\n--- Add New Student ---");
        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();
        OutputManager.log("> Student name provided: " + name);

        if (name.trim().isEmpty()) {
            OutputManager.log("[Error] Student name cannot be empty.");
            return;
        }

        gradebook.addStudent(name);
        OutputManager.log("Student '" + name + "' added successfully!");
    }

    private void promptEnterGrades() {
        OutputManager.log("\n--- Enter Student Grades ---");
        if (gradebook.getStudentList().isEmpty()) {
            OutputManager.log("No students have been added yet. Please add a student first.");
            return;
        }

        System.out.print("Enter the name of the student to add grades for: ");
        String name = scanner.nextLine();
        OutputManager.log("> Student name provided: " + name);

        Student student = gradebook.findStudent(name);
        if (student == null) {
            OutputManager.log("[Error] Student '" + name + "' not found.");
            return;
        }

        OutputManager.log("Enter a grade (0-100). Type 'done' when finished.");
        while (true) {
            System.out.print("Enter grade for " + student.getName() + " (or 'done'): ");
            String gradeInput = scanner.nextLine();
            OutputManager.log("> Grade input: " + gradeInput);

            if (gradeInput.equalsIgnoreCase("done")) {
                break;
            }
            try {
                int grade = Integer.parseInt(gradeInput);
                if (grade >= 0 && grade <= 100) {
                    student.addGrade(grade);
                    OutputManager.log("Grade " + grade + " added.");
                } else {
                    OutputManager.log("[Error] Invalid grade. Please enter a number between 0 and 100.");
                }
            } catch (NumberFormatException e) {
                OutputManager.log("[Error] Invalid input. Please enter a number or 'done'.");
            }
        }
        OutputManager.log("Grades updated for " + student.getName());
    }

    private void displayReport() {
        String report = gradebook.generatePerformanceReport();
        OutputManager.log(report);
    }
}