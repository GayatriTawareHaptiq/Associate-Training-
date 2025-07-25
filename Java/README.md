Student Grade Tracker & Reporter
A simple yet powerful console-based Java application for managing student grades. This project demonstrates core Java principles, object-oriented design, and file I/O by allowing users to add students, enter grades, and generate performance reports that are saved to a file.

📝 Overview
This application provides a command-line interface (CLI) for teachers or administrators to track student academic performance. Users can perform essential tasks like adding students to a class roster, inputting grades for each student, and generating a formatted class report. All interactions and generated reports are automatically logged to a text file for record-keeping.

✨ Features
Student Management: Add new students to the system.

Grade Entry: Input multiple grades (0-100) for each student.

Performance Reports: Display a detailed report on the console showing each student's average, minimum, and maximum grade, along with their pass/fail status.

Automatic Logging: All console output, including user actions and generated reports, is automatically saved to a grade_tracker_log.txt file for persistence.

User-Friendly CLI: A menu-driven interface makes the application easy to use.

Input Validation: Basic checks are in place to handle invalid input gracefully.

🚀 How to Run
Compile the Code:
Navigate to the project's directory where all the .java files are located and compile them.

javac com/example/gradeapp/\*.java

Run the Application:
Execute the Main class to start the application.

java com.example.gradeapp.Main

Use the Application:
Follow the on-screen menu prompts to add students, enter grades, and display reports.

Check the Log File:
After using the application, a file named grade_tracker_log.txt will appear in the same directory. This file contains a complete record of your session.

🧠 Core Concepts & Design Choices
This application is designed around several key programming principles to ensure it is organized, maintainable, and easy to understand.

Object-Oriented Design (OOD) & Separation of Concerns
Instead of having all the code in one giant file, the application is broken down into multiple classes, each with a single, well-defined responsibility. This is a fundamental concept called Separation of Concerns.

Student: A "model" class that only knows about the data for a single student.

Gradebook: A "manager" class that only knows how to manage the collection of students.

AppUI: A "view/controller" class that only knows how to interact with the user (display menus, get input).

OutputManager: A "utility" class that only knows how to handle logging to the console and a file.

Main: An "entry point" class whose only job is to start the application.

This separation makes the code cleaner, more reusable, and much easier to debug and maintain.

File I/O for Persistence
The OutputManager class uses Java's FileWriter to provide persistence. Every piece of information shown to the user on the console is also written to grade_tracker_log.txt. This creates an automatic audit trail of the application's use, ensuring data isn't lost when the application is closed.

📜 Class Pseudocode
Here is the high-level logic for each class in the application.

Main.java
CLASS Main
METHOD main():
// This is the starting point of the entire application.
START the OutputManager to open the log file.
TRY
CREATE a new Gradebook object to hold all student data.
CREATE a new AppUI object, giving it the Gradebook to work with.
CALL the start() method on the AppUI object to begin the main loop.
FINALLY
// This block always runs, even if an error occurs.
PRINT a shutdown message.
CLOSE the OutputManager to save the log file.

AppUI.java
CLASS AppUI
PROPERTIES: - a Scanner for user input - the Gradebook object

METHOD start():
DISPLAY a welcome message.
LOOP forever:
DISPLAY the main menu.
GET the user's menu choice.
IF choice is valid:
CALL handleMenuChoice() with the user's choice.
IF choice is to exit:
BREAK the loop.
DISPLAY a goodbye message.

METHOD handleMenuChoice(choice):
USE a switch statement based on the choice: - CASE 1: CALL promptAddStudent() - CASE 2: CALL promptEnterGrades() - CASE 3: CALL displayReport() - ...and so on.

METHOD promptAddStudent():
ASK for the student's name.
VALIDATE that the name is not empty.
TELL the Gradebook to add a new student with that name.

METHOD promptEnterGrades():
ASK for the name of the student.
FIND the student in the Gradebook.
IF found:
LOOP to ask for grades until the user types "done".
VALIDATE that the grade is a number between 0 and 100.
ADD each valid grade to the found student object.
ELSE:
SHOW "student not found" error.

METHOD displayReport():
ASK the Gradebook to generate the report string.
LOG the report string using the OutputManager.

Gradebook.java
CLASS Gradebook
PROPERTIES: - a list of Student objects

METHOD addStudent(name):
CREATE a new Student object with the given name.
ADD the new Student to the list.

METHOD findStudent(name):
LOOP through the list of students.
IF a student's name matches (ignoring case), RETURN that Student object.
RETURN null if no match is found.

METHOD generatePerformanceReport():
IF the student list is empty, RETURN a "no data" message.
CREATE an empty report string (using StringBuilder for efficiency).
ADD header text to the report.
LOOP through each Student in the list:
GET the student's average, min, and max grades from the Student object.
DETERMINE their pass/fail status based on the average.
FORMAT this data into a single line.
APPEND the line to the report string.
ADD footer text to the report.
RETURN the complete report string.

Student.java
CLASS Student
PROPERTIES: - name (String) - grades (a list of Integers)

CONSTRUCTOR(name):
SET this.name to the provided name.
INITIALIZE an empty list for grades.

METHOD addGrade(grade):
ADD the grade to the list of grades.

METHOD getAverageGrade():
CALCULATE and RETURN the average of all numbers in the grades list.

METHOD getMaxGrade():
FIND and RETURN the highest number in the grades list.

METHOD getMinGrade():
FIND and RETURN the lowest number in the grades list.

OutputManager.java
CLASS OutputManager
PROPERTIES: - a static FileWriter object (static so there's only one for the whole app) - a static final FILENAME constant

METHOD start():
CREATE a new FileWriter for the specified FILENAME.
HANDLE potential file creation errors.

METHOD log(message):
PRINT the message to the console for immediate user feedback.
WRITE the same message to the FileWriter object to log it to the file.

METHOD close():
FLUSH and CLOSE the FileWriter to ensure all buffered data is written to the file before the app exits.
