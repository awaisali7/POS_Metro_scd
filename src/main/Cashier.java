package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Cashier extends JFrame {
    private final List<Product> cart = new ArrayList<>(); // Cart to hold purchased items
    private final List<Product> inventory = new ArrayList<>(); // Inventory to manage stock

    public Cashier() {
        // Set frame properties
        setTitle("Cashier - Metro POS System");
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Add title label
        JLabel titleLabel = new JLabel("Cashier Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 139, 34)); // Dark green
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add buttons
        JButton addProductButton = new JButton("Add Product to Cart");
        JButton generateBillButton = new JButton("Generate Bill");
        buttonPanel.add(addProductButton);
        buttonPanel.add(generateBillButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Action listeners
        addProductButton.addActionListener(e -> showAddProductDialog());
        generateBillButton.addActionListener(e -> generateBill());
    }

    private void showAddProductDialog() {
        // Dialog for adding a product to the cart
        JDialog dialog = new JDialog(this, "Add Product to Cart", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));

        // Form fields
        JTextField productNameField = new JTextField();
        JTextField quantityField = new JTextField();

        // Add components
        dialog.add(new JLabel("Product Name:"));
        dialog.add(productNameField);
        dialog.add(new JLabel("Quantity:"));
        dialog.add(quantityField);

        // Buttons
        JButton addButton = new JButton("Add to Cart");
        JButton cancelButton = new JButton("Cancel");
        dialog.add(addButton);
        dialog.add(cancelButton);

        // Add button action
        addButton.addActionListener(e -> {
            String productName = productNameField.getText();
            String quantityText = quantityField.getText();

            if (productName.isEmpty() || quantityText.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    int quantity = Integer.parseInt(quantityText);

                    // Check inventory for the product
                    Product product = findProductInInventory(productName);
                    if (product == null) {
                        JOptionPane.showMessageDialog(dialog, "Product not found in inventory.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (product.getStock() < quantity) {
                        JOptionPane.showMessageDialog(dialog, "Insufficient stock available.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Add to cart and reduce inventory stock
                        cart.add(new Product(product.getName(), product.getPrice(), quantity));
                        product.reduceStock(quantity);
                        JOptionPane.showMessageDialog(dialog, "Product added to cart.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dialog.dispose();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid quantity value.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Cancel button action
        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void generateBill() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No products in the cart.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder bill = new StringBuilder("Bill Details:\n\n");
        double total = 0;

        for (Product product : cart) {
            bill.append(String.format("%s - %d x %.2f = %.2f\n",
                    product.getName(), product.getQuantity(), product.getPrice(), product.getTotalPrice()));
            total += product.getTotalPrice();
        }

        bill.append("\nTotal: ").append(String.format("%.2f", total));
        JOptionPane.showMessageDialog(this, bill.toString(), "Bill Generated", JOptionPane.INFORMATION_MESSAGE);

        // Clear the cart after generating the bill
        cart.clear();
    }

    private Product findProductInInventory(String productName) {
        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Initialize inventory (sample data)
        Cashier cashier = new Cashier();
        cashier.inventory.add(new Product("Apple", 1.5, 100));
        cashier.inventory.add(new Product("Banana", 0.8, 150));
        cashier.inventory.add(new Product("Orange", 1.2, 120));

        SwingUtilities.invokeLater(() -> cashier.setVisible(true));
    }
}

// Helper class to represent a Product
class Product {
    private final String name;
    private final double price;
    private int stock;
    private int quantity;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    public void reduceStock(int amount) {
        this.stock -= amount;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Price: %.2f, Stock: %d", name, price, stock);
    }
}
