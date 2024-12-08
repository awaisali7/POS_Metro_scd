package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TransactionHistoryPage {
    private final Database database;

    public TransactionHistoryPage(Database database) {
        this.database = database;
    }

    public void viewTransactionHistory() {
        String sql = "SELECT * FROM transactions";

        try (Connection connection = database.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n=== Transaction History ===");
            while (rs.next()) {
                System.out.printf("Transaction ID: %d, Product ID: %d, Quantity: %d, Total Price: %.2f, Date: %s%n",
                        rs.getInt("id"), rs.getInt("product_id"),
                        rs.getInt("quantity"), rs.getDouble("total_price"),
                        rs.getDate("transaction_date"));
            }
        } catch (Exception e) {
            System.err.println("Failed to retrieve transactions: " + e.getMessage());
        }
    }
}
