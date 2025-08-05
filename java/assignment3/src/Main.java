import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 1. Start with a list that includes pre-existing employees.
        List<Employee> employees = getInitialEmployees();

        System.out.println("--- HR Data Entry ---");
        System.out.println("Loaded " + employees.size() + " existing employees.");

        // 2. Loop to allow adding NEW employees to the SAME list.
        while (true) {
            System.out.print("\nDo you want to add a new employee? (yes/no): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                break;
            }

            try {
                System.out.println("\nEnter details for the new employee:");
                System.out.print("ID: ");
                int id = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Name: ");
                String name = scanner.nextLine();

                System.out.print("Department: ");
                String department = scanner.nextLine();

                System.out.print("Salary: ");
                double salary = scanner.nextDouble();

                System.out.print("Experience (in years): ");
                int experience = scanner.nextInt();
                scanner.nextLine();

                // Add the new employee to our list.
                employees.add(new Employee(id, name, department, salary, experience));

            }
            //invallid user input exception
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the correct data type. Let's try that employee again.");
                scanner.nextLine();
            }
        }


        if (employees.isEmpty()) {
            System.out.println("\nNo employees to analyze. Exiting.");
            return;
        }

        System.out.println("\n\n--- Starting Data Analysis on " + employees.size() + " total employees ---");
        HRAnalysisTool tool = new HRAnalysisTool();

        System.out.println("\n--- High Earners (> 50k) ---");
        List<Employee> highEarners = tool.getHighEarners(employees);
        highEarners.forEach(System.out::println);

        System.out.println("\n--- Employees Grouped by Department ---");
        Map<String, List<Employee>> byDept = tool.groupEmployeesByDepartment(employees);
        byDept.forEach((dept, emps) -> System.out.println(dept + ": " + emps));

        System.out.println("\n--- Average Salary by Department ---");
        Map<String, Double> avgSalary = tool.getAverageSalaryByDepartment(employees);
        avgSalary.forEach((dept, avg) -> System.out.printf("%s: ₹%.2f%n", dept, avg));

        System.out.println("\n--- Employees Sorted by Experience (Desc) then Salary (Desc) ---");
        List<Employee> sortedEmployees = tool.getEmployeesSortedByExperienceAndSalary(employees);
        sortedEmployees.forEach(System.out::println);
    }


     //return A list of initial employees.

    private static List<Employee> getInitialEmployees() {
        List<Employee> initialList = new ArrayList<>();
        initialList.add(new Employee(1, "Alice", "Engineering", 80000, 5));
        initialList.add(new Employee(2, "Bob", "HR", 45000, 3));
        initialList.add(new Employee(3, "Charlie", "Engineering", 95000, 8));
        initialList.add(new Employee(4, "David", "Marketing", 60000, 5));
        initialList.add(new Employee(5, "Eve", "HR", 52000, 4));
        return initialList;
    }
}
