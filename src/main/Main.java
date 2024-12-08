package main;

import java.util.Scanner;

public class Main {
    private static Database database;

    public static void main(String[] args) {
        // Initialize the database connection
        database = new Database();

        if (!database.isConnected()) {
            System.err.println("Could not connect to the database. Exiting...");
            return;
        }

        // Start the POS System
        System.out.println("Welcome to Metro POS System!");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = getChoice(scanner);

            switch (choice) {
                case 1:
                    addBranch(scanner);
                    break;
                case 2:
                    addEmployee(scanner);
                    break;
                case 3:
                    addProduct(scanner);
                    break;
                case 4:
                    generateReports();
                    break;
                case 5:
                    exitSystem();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Add Branch");
        System.out.println("2. Add Employee");
        System.out.println("3. Add Product");
        System.out.println("4. Generate Reports");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void addBranch(Scanner scanner) {
        System.out.println("\n=== Add Branch ===");
        System.out.print("Enter Branch Code: ");
        String branchCode = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter City: ");
        String city = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        database.saveBranch(branchCode, name, city, address, phone);
    }

    private static void addEmployee(Scanner scanner) {
        System.out.println("\n=== Add Employee ===");
        System.out.print("Enter Employee ID: ");
        String empId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Role: ");
        String role = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Branch Code: ");
        String branchCode = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = Double.parseDouble(scanner.nextLine());

        database.saveEmployee(empId, name, role, email, branchCode, salary);
    }

    private static void addProduct(Scanner scanner) {
        System.out.println("\n=== Add Product ===");
        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        System.out.print("Enter Original Price: ");
        double originalPrice = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter Sale Price: ");
        double salePrice = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter Stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        database.saveProduct(name, category, originalPrice, salePrice, stock);
    }

    private static void generateReports() {
        System.out.println("\n=== Generate Reports ===");
        // Add logic to integrate `ReportGenerator` here
        // Example: Call ReportGenerator.generateBarChart(...) or generatePieChart(...)
        System.out.println("Report generation is not implemented yet.");
    }

    private static void exitSystem() {
        System.out.println("\nExiting the Metro POS System...");
        database.close();
        System.out.println("Goodbye!");
    }
}
