package main;

import java.sql.*;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/metro_pos";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password"; // Replace with your actual password

    private Connection connection;

    // Constructor to establish the connection
    public Database() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            logError("Failed to connect to the database", e);
        }
    }

    // Utility method to check if the connection is valid
    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            logError("Error checking database connection", e);
            return false;
        }
    }

    // Save a branch record
    public void saveBranch(String branchCode, String name, String city, String address, String phone) {
        String sql = "INSERT INTO branches (branch_code, name, city, address, phone) VALUES (?, ?, ?, ?, ?)";
        executeUpdate(sql, branchCode, name, city, address, phone);
    }

    // Save an employee record
    public void saveEmployee(String empId, String name, String role, String email, String branchCode, double salary) {
        String sql = "INSERT INTO employees (emp_id, name, role, email, branch_code, salary) VALUES (?, ?, ?, ?, ?, ?)";
        executeUpdate(sql, empId, name, role, email, branchCode, salary);
    }

    // Save a product record
    public void saveProduct(String name, String category, double originalPrice, double salePrice, int stock) {
        String sql = "INSERT INTO products (name, category, original_price, sale_price, stock) VALUES (?, ?, ?, ?, ?)";
        executeUpdate(sql, name, category, originalPrice, salePrice, stock);
    }

    // Generic method for executing parameterized SQL updates
    private void executeUpdate(String sql, Object... parameters) {
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.length; i++) {
                stmt.setObject(i + 1, parameters[i]);
            }
            stmt.executeUpdate();
            System.out.println("Operation completed successfully.");
        } catch (SQLException e) {
            logError("Failed to execute SQL update", e);
        }
    }

    // Utility method to log SQL errors
    private void logError(String message, SQLException e) {
        System.err.println(message + ": " + e.getMessage());
        e.printStackTrace();
    }

    // Close the database connection
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            logError("Failed to close database connection", e);
        }
    }
}
