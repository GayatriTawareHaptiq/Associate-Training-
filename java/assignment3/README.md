HR Data Analysis Tool
A console-based Java application designed to process and analyze employee data. This project serves as a practical demonstration of the Java Collections Framework, Generics, Lambdas, and the powerful Java Stream API for performing complex data manipulations in a clean, functional style.

📝 Overview
This application provides an interactive command-line interface (CLI) for an HR department. It starts with a pre-existing list of employees (simulating a database) and allows the user to add new employees. Once the data entry is complete, the tool performs a series of analyses—such as filtering, grouping, averaging, and sorting—and displays the insightful results on the console.

✨ Features
Interactive Data Entry: Allows users to add new employees to an existing roster via the console.

Data Filtering: Filters employees based on specific criteria (e.g., salary > ₹50,000).

Data Grouping: Groups employees by department to see the organizational structure.

Aggregate Calculations: Calculates aggregate data, such as the average salary per department.

Complex Sorting: Sorts employees based on multiple criteria (e.g., by experience, then by salary).

Reusable Utilities: Includes a generic, reusable method to filter any list based on any condition, showcasing the power of Generics and functional interfaces.

🧠 Core Concepts & Design Choices
This project is built on several modern Java concepts to write efficient and readable code.

Separation of Concerns
The application is broken down into multiple classes, each with a single, well-defined responsibility. This makes the code organized and easy to maintain.

Employee: A "model" class that only holds the data for a single employee.

HRAnalysisTool: A "service" class containing the core business logic for all data analysis tasks.

StreamUtils: A "utility" class with a reusable, generic filtering method.

Main: The "entry point" or "driver" class that handles user interaction and orchestrates the application flow.

Java Stream API
This is the central concept of the project. Instead of writing complex for loops and if statements, we use the Stream API to create a declarative "pipeline" of operations.

Why this concept? Streams allow you to express complex data processing logic concisely and clearly. You describe what you want to do (e.g., "filter, then sort, then collect"), not how to do it step-by-step. This leads to more readable and less error-prone code.

How it's used: Every method in HRAnalysisTool starts with employees.stream() and then chains together operations like filter(), sorted(), and collect().

Lambdas & Method References
Why this concept? Lambdas and method references are the "glue" that makes the Stream API work. They are short, anonymous functions that you can pass into stream methods to provide the specific logic for an operation.

How it's used:

Lambda: emp -> emp.getSalary() > 50000 is a lambda expression passed to the filter() method. It's a quick, inline function that returns true or false.

Method Reference: Employee::getDepartment is a method reference passed to groupingBy(). It's a compact shortcut for the lambda emp -> emp.getDepartment().

Generics (<T>) and Predicate<T>
Why this concept? These are used in the StreamUtils class to create a truly reusable function.

How it's used:

<T> makes the filterList method generic, meaning it can work on a List<Employee>, a List<String>, or a list of any other object type.

Predicate<T> is a functional interface that represents a function that takes an object and returns true or false. By accepting a Predicate as a parameter, our filterList method can be used with any filtering rule imaginable.

📜 Class Pseudocode
Here is the high-level logic for each class in the application.

Main.java
CLASS Main
METHOD main():
// This is the starting point of the entire application.
CREATE a Scanner for user input.
CREATE a mutable list of Employees and PRE-POPULATE it with initial data.
PRINT a welcome message.

    LOOP as long as the user wants to add employees:
        PROMPT the user if they want to add a new employee. If "no", break the loop.
        TRY
            GET all employee details (ID, name, salary, etc.) from the user.
            CREATE a new Employee object from the input.
            ADD the new Employee to the list.
        CATCH any input errors (like typing text for a number) and print a helpful message.

    IF the employee list is not empty:
        PRINT a "Starting Analysis" message.
        CREATE an instance of the HRAnalysisTool.
        CALL each method on the tool, passing the combined employee list.
        PRINT the results from each method in a formatted way.
    ELSE:
        PRINT "No employees were entered."

HRAnalysisTool.java
CLASS HRAnalysisTool

METHOD getHighEarners(list_of_employees): - START with the list of employees. - CREATE a stream of employees. - FILTER the stream, keeping only employees where salary > 50000. - COLLECT the filtered employees into a new list. - RETURN the new list.

METHOD groupEmployeesByDepartment(list_of_employees): - START with the list of employees. - CREATE a stream of employees. - COLLECT the stream into a Map, using the department name as the key and a list of employees as the value. - RETURN the resulting Map.

METHOD getAverageSalaryByDepartment(list_of_employees): - START with the list of employees. - CREATE a stream of employees. - COLLECT the stream into a Map, using the department name as the key. - FOR each group, calculate the average of their salaries and use that as the value. - RETURN the resulting Map.

METHOD getEmployeesSortedByExperienceAndSalary(list_of_employees): - START with the list of employees. - CREATE a stream of employees. - SORT the stream using a two-level comparison: 1. First, by experience in descending order. 2. Then, for employees with the same experience, by salary in descending order. - COLLECT the sorted employees into a new list. - RETURN the new list.

Employee.java
CLASS Employee
PROPERTIES: - id (integer) - name (String) - department (String) - salary (double) - experienceInYears (integer)

CONSTRUCTOR(id, name, department, salary, experience): - Initialize all properties.

GETTER methods for each property (e.g., getId(), getName()).

// These methods are important for object comparison and printing.
METHOD equals(otherObject): - Check if the otherObject is an Employee with the exact same property values.
METHOD hashCode(): - Generate a unique number (hash code) based on all properties.
METHOD toString(): - Return a nicely formatted string representing the employee's data.

StreamUtils.java
CLASS StreamUtils

// A generic method that works for any type <T>
METHOD filterList(a_list_of_type_T, a_filtering_rule_of_type_Predicate<T>): - START with the list. - CREATE a stream from the list. - FILTER the stream using the provided filtering rule. - COLLECT the results into a new list. - RETURN the new list.
