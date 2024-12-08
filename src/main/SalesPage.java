package main;

import java.util.Scanner;

public class SalesPage {
    private final Database database;

    public SalesPage(Database database) {
        this.database = database;
    }

    public void processSale(Scanner scanner) {
        System.out.println("\n=== Sales Management ===");
        database.getAllProducts().forEach(System.out::println);

        System.out.print("Enter Product ID to sell: ");
        int productId = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        // Validate and process the sale
        Product product = database.getAllProducts()
                .stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElse(null);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        if (quantity > product.getStock()) {
            System.out.println("Insufficient stock available.");
            return;
        }

        database.reduceProductStock(productId, quantity);
        System.out.println("Sale completed successfully!");
    }
}
