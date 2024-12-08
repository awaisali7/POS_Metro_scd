package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerManagementPage {
    private final Database database;

    public CustomerManagementPage(Database database) {
        this.database = database;
    }

    public void manageCustomers(Scanner scanner) {
        System.out.println("\n=== Customer Management ===");
        System.out.println("1. Add Customer");
        System.out.println("2. View Customers");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> addCustomer(scanner);
            case 2 -> viewCustomers();
            default -> System.out.println("Invalid choice.");
        }
    }

    private void addCustomer(Scanner scanner) {
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Customer Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Customer Phone: ");
        String phone = scanner.nextLine();

        String sql = "INSERT INTO customers (name, email, phone) VALUES (?, ?, ?)";

        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.executeUpdate();
            System.out.println("Customer added successfully!");
        } catch (Exception e) {
            System.err.println("Failed to add customer: " + e.getMessage());
        }
    }

    private void viewCustomers() {
        String sql = "SELECT * FROM customers";

        try (Connection connection = database.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n=== Customer List ===");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Email: %s, Phone: %s%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("phone"));
            }
        } catch (Exception e) {
            System.err.println("Failed to retrieve customers: " + e.getMessage());
        }
    }
}
