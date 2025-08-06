Inventory Management System
This is a console-based Java application for managing a store's inventory. It connects to a MySQL database using JDBC and allows users to perform standard CRUD (Create, Read, Update, Delete) operations on products, manage stock levels, and record transactions.

The project was developed as part of a training assignment focusing on core Java concepts, database connectivity, and transaction management.

✨ Features
Product Management: Full CRUD operations (Add, Update, Delete) for products.

Stock Tracking: The system updates stock levels automatically when a SALE or RESTOCK transaction is recorded.

Database Transactions: Uses JDBC transaction management (commit/rollback) to ensure data integrity during stock updates.

Pagination: Displays products in a paginated view, allowing you to navigate through large lists.

Input Validation: Handles user input to prevent errors and invalid data.

SQL Injection Prevention: Uses PreparedStatement to secure database queries.

🛠️ Prerequisites
To run this project, you will need to have the following installed:

Java Development Kit (JDK) 17 or higher

Maven (IntelliJ IDEA comes with a built-in Maven)

MySQL Server

IntelliJ IDEA (or another IDE that supports Maven)

🚀 Setup & Run
Follow these steps to get the application up and running.

1. Database Setup
   First, you need to set up the MySQL database and tables.

Open your MySQL command-line client or a GUI tool like MySQL Workbench.

Run the following SQL commands to create the database and its schema:

SQL

-- Create the database
CREATE DATABASE inventory_management;
USE inventory_management;

-- Create the categories table
CREATE TABLE categories (
category_id INT PRIMARY KEY AUTO_INCREMENT,
category_name VARCHAR(255) NOT NULL UNIQUE
);

-- Create the products table
CREATE TABLE products (
product_id INT PRIMARY KEY AUTO_INCREMENT,
product_name VARCHAR(255) NOT NULL,
price DECIMAL(10, 2) NOT NULL,
stock INT NOT NULL,
category_id INT,
FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

-- Create the transactions table
CREATE TABLE transactions (
transaction_id INT PRIMARY KEY AUTO_INCREMENT,
product_id INT,
transaction_type ENUM('SALE', 'RESTOCK') NOT NULL,
quantity INT NOT NULL,
transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- Optional: Insert some initial data
INSERT INTO categories (category_name) VALUES ('Electronics'), ('Books'), ('Groceries');
INSERT INTO products (product_name, price, stock, category_id) VALUES ('Laptop', 1200.00, 50, 1), ('Java Programming Book', 55.50, 100, 2), ('Apples', 1.50, 200, 3);
2. Project Setup in IntelliJ IDEA
   Open IntelliJ IDEA and create a New Project with Maven as the build system.

Open the pom.xml file and add the following dependency for the MySQL JDBC driver inside the <dependencies> tag:

XML

<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>
</dependencies>
Click on the "Reload Maven Projects" button (a refresh icon in the Maven tool window) to download the dependency.

Create a package (e.g., com.example.inventory) and add the three Java files (DatabaseConnection.java, Product.java, InventoryManagementApp.java).

3. Configure Database Credentials
   Open DatabaseConnection.java and update the USER and PASS variables with your MySQL credentials.

Java

// DatabaseConnection.java
private static final String DB_URL = "jdbc:mysql://localhost:3306/inventory_management";
private static final String USER = "your_username"; // e.g., "root"
private static final String PASS = "your_password"; // your MySQL password
4. Run the Application
   Open the InventoryManagementApp.java file.

Click the green "play" icon next to the main method.

The application will start, and the menu will be displayed in the console at the bottom of the screen.

🖥️ Usage
Interact with the application by entering the numbers corresponding to the menu options:

--- Store Inventory Management ---
1. Add a new product
2. Update an existing product
3. Delete a product
4. Display all products (with pagination)
5. Record a transaction (Sale/Restock)
6. Exit
   Technologies Used:

Java

JDBC

Maven

MySQL