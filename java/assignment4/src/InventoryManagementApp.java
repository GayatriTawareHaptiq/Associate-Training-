import java.sql.*;
import java.util.Scanner;

public class InventoryManagementApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int PAGE_SIZE = 5;

    public static void main(String[] args) {
        int choice;
        do {
            showMainMenu();
            choice = getIntInput("Enter your choice: ");
            try {
                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        updateProduct();
                        break;
                    case 3:
                        deleteProduct();
                        break;
                    case 4:
                        displayProducts();
                        break;
                    case 5:
                        recordTransaction();
                        break;
                    case 6:
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }
        } while (choice != 6);
        scanner.close();
    }

    private static void showMainMenu() {
        System.out.println("\n--- Store Inventory Management ---");
        System.out.println("1. Add a new product");
        System.out.println("2. Update an existing product");
        System.out.println("3. Delete a product");
        System.out.println("4. Display all products (with pagination)");
        System.out.println("5. Record a transaction (Sale/Restock)");
        System.out.println("6. Exit");
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // --- CRUD Operations ---

    private static void addProduct() throws SQLException {
        System.out.println("\n--- Add New Product ---");
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        double price = getDoubleInput("Enter price: ");
        int stock = getIntInput("Enter initial stock: ");
        int categoryId = getIntInput("Enter category ID: ");

        String sql = "INSERT INTO products (product_name, price, stock, category_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, stock);
            pstmt.setInt(4, categoryId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product added successfully!");
            } else {
                System.out.println("Failed to add product.");
            }
        }
    }

    private static void updateProduct() throws SQLException {
        System.out.println("\n--- Update Product ---");
        int id = getIntInput("Enter product ID to update: ");
        System.out.print("Enter new product name (leave blank to keep current): ");
        String name = scanner.nextLine();
        double price = getDoubleInput("Enter new price (0 to keep current): ");
        int stock = getIntInput("Enter new stock (0 to keep current): ");

        String sql = "UPDATE products SET ";
        boolean isFirst = true;

        if (!name.isEmpty()) {
            sql += "product_name = ?";
            isFirst = false;
        }
        if (price > 0) {
            if (!isFirst) sql += ", ";
            sql += "price = ?";
            isFirst = false;
        }
        if (stock >= 0) {
            if (!isFirst) sql += ", ";
            sql += "stock = ?";
            isFirst = false;
        }

        sql += " WHERE product_id = ?";

        if (isFirst) {
            System.out.println("No fields to update.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int paramIndex = 1;

            if (!name.isEmpty()) {
                pstmt.setString(paramIndex++, name);
            }
            if (price > 0) {
                pstmt.setDouble(paramIndex++, price);
            }
            if (stock >= 0) {
                pstmt.setInt(paramIndex++, stock);
            }

            pstmt.setInt(paramIndex, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Product with ID " + id + " not found.");
            }
        }
    }

    private static void deleteProduct() throws SQLException {
        System.out.println("\n--- Delete Product ---");
        int id = getIntInput("Enter product ID to delete: ");

        String sql = "DELETE FROM products WHERE product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully!");
            } else {
                System.out.println("Product with ID " + id + " not found.");
            }
        }
    }

    private static void displayProducts() throws SQLException {
        int totalProducts = getTotalProductCount();
        int totalPages = (int) Math.ceil((double) totalProducts / PAGE_SIZE);
        int currentPage = 1;

        if (totalProducts == 0) {
            System.out.println("No products found in the inventory.");
            return;
        }

        while (true) {
            System.out.println("\n--- Products (Page " + currentPage + " of " + totalPages + ") ---");
            int offset = (currentPage - 1) * PAGE_SIZE;

            String sql = "SELECT product_id, product_name, price, stock, category_id FROM products LIMIT ? OFFSET ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, PAGE_SIZE);
                pstmt.setInt(2, offset);

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        System.out.println(new Product(
                                rs.getInt("product_id"),
                                rs.getString("product_name"),
                                rs.getDouble("price"),
                                rs.getInt("stock"),
                                rs.getInt("category_id")
                        ));
                    }
                }
            }

            if (totalPages > 1) {
                System.out.println("\n'n' for next page, 'p' for previous page, 'm' for main menu.");
                String input = scanner.nextLine().toLowerCase();
                if (input.equals("n")) {
                    currentPage = Math.min(currentPage + 1, totalPages);
                } else if (input.equals("p")) {
                    currentPage = Math.max(currentPage - 1, 1);
                } else if (input.equals("m")) {
                    break;
                } else {
                    System.out.println("Invalid command. Returning to main menu.");
                    break;
                }
            } else {
                System.out.println("No more pages. Returning to main menu.");
                break;
            }
        }
    }

    private static int getTotalProductCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM products";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    private static void recordTransaction() throws SQLException {
        System.out.println("\n--- Record Transaction ---");
        int productId = getIntInput("Enter product ID: ");
        String transactionType = "";
        int quantity = 0;

        while (true) {
            System.out.print("Enter transaction type (SALE or RESTOCK): ");
            String type = scanner.nextLine().toUpperCase();
            if (type.equals("SALE") || type.equals("RESTOCK")) {
                transactionType = type;
                break;
            } else {
                System.out.println("Invalid transaction type. Please enter 'SALE' or 'RESTOCK'.");
            }
        }
        quantity = getIntInput("Enter quantity: ");
        if (quantity <= 0) {
            System.out.println("Quantity must be a positive number.");
            return;
        }

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            String getStockSql = "SELECT stock FROM products WHERE product_id = ?";
            int currentStock = 0;
            try (PreparedStatement pstmt = conn.prepareStatement(getStockSql)) {
                pstmt.setInt(1, productId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    currentStock = rs.getInt("stock");
                } else {
                    System.out.println("Product with ID " + productId + " not found.");
                    conn.rollback();
                    return;
                }
            }

            String updateStockSql = "UPDATE products SET stock = ? WHERE product_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateStockSql)) {
                int newStock = 0;
                if (transactionType.equals("SALE")) {
                    if (currentStock < quantity) {
                        System.out.println("Insufficient stock for sale. Transaction aborted.");
                        conn.rollback();
                        return;
                    }
                    newStock = currentStock - quantity;
                } else {
                    newStock = currentStock + quantity;
                }
                pstmt.setInt(1, newStock);
                pstmt.setInt(2, productId);
                pstmt.executeUpdate();
            }

            String recordTransactionSql = "INSERT INTO transactions (product_id, transaction_type, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(recordTransactionSql)) {
                pstmt.setInt(1, productId);
                pstmt.setString(2, transactionType);
                pstmt.setInt(3, quantity);
                pstmt.executeUpdate();
            }

            conn.commit();
            System.out.println("Transaction recorded successfully!");

        } catch (SQLException e) {
            System.err.println("Transaction failed: " + e.getMessage());
            if (conn != null) {
                try {
                    System.err.println("Attempting to rollback changes.");
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Rollback failed: " + rollbackEx.getMessage());
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    System.err.println("Failed to close connection: " + closeEx.getMessage());
                }
            }
        }
    }
}