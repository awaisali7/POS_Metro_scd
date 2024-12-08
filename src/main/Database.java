package main;

import java.sql.*;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/metro_pos";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password"; // Replace with your actual password

    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            System.err.println("Error checking database connection: " + e.getMessage());
            return false;
        }
    }

    public void saveBranch(String branchCode, String name, String city, String address, String phone) {
        String sql = "INSERT INTO branches (branch_code, name, city, address, phone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, branchCode);
            stmt.setString(2, name);
            stmt.setString(3, city);
            stmt.setString(4, address);
            stmt.setString(5, phone);
            stmt.executeUpdate();
            System.out.println("Branch saved successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to save branch: " + e.getMessage());
        }
    }

    public void saveEmployee(String empId, String name, String role, String email, String branchCode, double salary) {
        String sql = "INSERT INTO employees (emp_id, name, role, email, branch_code, salary) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, empId);
            stmt.setString(2, name);
            stmt.setString(3, role);
            stmt.setString(4, email);
            stmt.setString(5, branchCode);
            stmt.setDouble(6, salary);
            stmt.executeUpdate();
            System.out.println("Employee saved successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to save employee: " + e.getMessage());
        }
    }

    public void saveProduct(String name, String category, double originalPrice, double salePrice, int stock) {
        String sql = "INSERT INTO products (name, category, original_price, sale_price, stock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, category);
            stmt.setDouble(3, originalPrice);
            stmt.setDouble(4, salePrice);
            stmt.setInt(5, stock);
            stmt.executeUpdate();
            System.out.println("Product saved successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to save product: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to close database connection: " + e.getMessage());
        }
    }
}
