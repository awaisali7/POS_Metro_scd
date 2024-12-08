package main;

import java.util.Scanner;

public class EmployeeManagementPage {
    private final Database database;

    public EmployeeManagementPage(Database database) {
        this.database = database;
    }

    public void manageEmployees(Scanner scanner) {
        System.out.println("\n=== Employee Management ===");
        System.out.println("1. View Employees");
        System.out.println("2. Update Employee Salary");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> viewEmployees();
            case 2 -> updateEmployeeSalary(scanner);
            default -> System.out.println("Invalid choice.");
        }
    }

    private void viewEmployees() {
        System.out.println("\n=== Employee List ===");
        // Replace this with a method to fetch employees from your database.
        System.out.println("Employee fetching functionality not implemented yet.");
    }

    private void updateEmployeeSalary(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        String empId = scanner.nextLine();

        System.out.print("Enter New Salary: ");
        double newSalary = Double.parseDouble(scanner.nextLine());

        String sql = "UPDATE employees SET salary = ? WHERE emp_id = ?";
        try (var connection = database.getConnection();
             var stmt = connection.prepareStatement(sql)) {

            stmt.setDouble(1, newSalary);
            stmt.setString(2, empId);
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Employee salary updated successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (Exception e) {
            System.err.println("Failed to update employee salary: " + e.getMessage());
        }
    }
}
