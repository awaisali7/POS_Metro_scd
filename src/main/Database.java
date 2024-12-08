package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    // Fetch all products in the inventory
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("original_price"),
                        rs.getDouble("sale_price"),
                        rs.getInt("stock")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch products: " + e.getMessage());
        }
        return products;
    }

    // Update stock of a product
    public void updateProductStock(int productId, int newStock) {
        String sql = "UPDATE products SET stock = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, newStock);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
            System.out.println("Stock updated successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to update stock: " + e.getMessage());
        }
    }

    // Reduce stock during a transaction
    public void reduceProductStock(int productId, int quantity) {
        String sql = "UPDATE products SET stock = stock - ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
            System.out.println("Stock reduced successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to reduce stock: " + e.getMessage());
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
