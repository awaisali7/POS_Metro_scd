package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SupplierManagementPage {
    private final Database database;

    public SupplierManagementPage(Database database) {
        this.database = database;
    }

    public void manageSuppliers(Scanner scanner) {
        System.out.println("\n=== Supplier Management ===");
        System.out.println("1. Add Supplier");
        System.out.println("2. View Suppliers");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> addSupplier(scanner);
            case 2 -> viewSuppliers();
            default -> System.out.println("Invalid choice.");
        }
    }

    private void addSupplier(Scanner scanner) {
        System.out.print("Enter Supplier Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Supplier Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Supplier Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Supplier Address: ");
        String address = scanner.nextLine();

        String sql = "INSERT INTO suppliers (name, email, phone, address) VALUES (?, ?, ?, ?)";

        try (Connection connection = database.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, address);
            stmt.executeUpdate();
            System.out.println("Supplier added successfully!");
        } catch (Exception e) {
            System.err.println("Failed to add supplier: " + e.getMessage());
        }
    }

    private void viewSuppliers() {
        String sql = "SELECT * FROM suppliers";

        try (Connection connection = database.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n=== Supplier List ===");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Email: %s, Phone: %s, Address: %s%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("phone"),
                        rs.getString("address"));
            }
        } catch (Exception e) {
            System.err.println("Failed to retrieve suppliers: " + e.getMessage());
        }
    }
}
