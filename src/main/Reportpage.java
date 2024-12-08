package main;

import utils.ReportGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReportsPage {

    public void generateReports(Scanner scanner) {
        System.out.println("\n=== Reports ===");
        System.out.println("1. Sales Report");
        System.out.println("2. Inventory Report");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> generateSalesReport();
            case 2 -> generateInventoryReport();
            default -> System.out.println("Invalid choice.");
        }
    }

    private void generateSalesReport() {
        // Example sales data
        Map<String, Double> salesData = new HashMap<>();
        salesData.put("Branch A", 15000.0);
        salesData.put("Branch B", 22000.0);
        salesData.put("Branch C", 18000.0);

        ReportGenerator.generateBarChart("Sales Report", salesData, "Branches", "Sales");
    }

    private void generateInventoryReport() {
        // Example inventory data
        Map<String, Double> inventoryData = new HashMap<>();
        inventoryData.put("Product A", 50.0);
        inventoryData.put("Product B", 30.0);
        inventoryData.put("Product C", 20.0);

        ReportGenerator.generatePieChart("Inventory Report", inventoryData);
    }
}
