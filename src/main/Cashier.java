package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cashier extends JFrame {
    private final List<Product> inventory = new ArrayList<>(); // Inventory to manage stock
    private final DefaultTableModel cartTableModel; // Table model for cart

    public Cashier() {
        // Set frame properties
        setTitle("Cashier - Metro POS System");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Title label
        JLabel titleLabel = new JLabel("Cashier Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(34, 139, 34)); // Dark green
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Cart table
        cartTableModel = new DefaultTableModel(new String[]{"Name", "Price", "Quantity", "Discount (%)", "Total"}, 0);
        JTable cartTable = new JTable(cartTableModel);
        JScrollPane scrollPane = new JScrollPane(cartTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton addProductButton = new JButton("Add Product");
        JButton generateBillButton = new JButton("Generate Bill");
        JButton clearCartButton = new JButton("Clear Cart");

        buttonPanel.add(addProductButton);
        buttonPanel.add(generateBillButton);
        buttonPanel.add(clearCartButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // Add action listeners
        addProductButton.addActionListener(e -> showAddProductDialog());
        generateBillButton.addActionListener(e -> generateBill());
        clearCartButton.addActionListener(e -> cartTableModel.setRowCount(0));
    }

    private void showAddProductDialog() {
        // Dialog for adding a product to the cart
        JDialog dialog = new JDialog(this, "Add Product to Cart", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));

        JTextField productNameField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField discountField = new JTextField();

        dialog.add(new JLabel("Product Name:"));
        dialog.add(productNameField);
        dialog.add(new JLabel("Quantity:"));
        dialog.add(quantityField);
        dialog.add(new JLabel("Discount (%):"));
        dialog.add(discountField);

        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
        dialog.add(addButton);
        dialog.add(cancelButton);

        addButton.addActionListener(e -> {
            String productName = productNameField.getText();
            String quantityText = quantityField.getText();
            String discountText = discountField.getText();

            if (productName.isEmpty() || quantityText.isEmpty() || discountText.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int quantity = Integer.parseInt(quantityText);
                double discount = Double.parseDouble(discountText);

                Product product = findProductInInventory(productName);
                if (product == null) {
                    JOptionPane.showMessageDialog(dialog, "Product not found in inventory.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (product.getStock() < quantity) {
                    JOptionPane.showMessageDialog(dialog, "Insufficient stock available.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    double discountedPrice = product.getPrice() - (product.getPrice() * discount / 100);
                    double total = discountedPrice * quantity;

                    // Add to table
                    cartTableModel.addRow(new Object[]{productName, product.getPrice(), quantity, discount, total});

                    // Reduce inventory stock
                    product.reduceStock(quantity);

                    JOptionPane.showMessageDialog(dialog, "Product added to cart.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid quantity or discount value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());
        dialog.setVisible(true);
    }

    private void generateBill() {
        if (cartTableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No products in the cart.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder bill = new StringBuilder("Bill Details:\n\n");
        double grandTotal = 0;

        for (int i = 0; i < cartTableModel.getRowCount(); i++) {
            String name = (String) cartTableModel.getValueAt(i, 0);
            double price = (double) cartTableModel.getValueAt(i, 1);
            int quantity = (int) cartTableModel.getValueAt(i, 2);
            double discount = (double) cartTableModel.getValueAt(i, 3);
            double total = (double) cartTableModel.getValueAt(i, 4);

            bill.append(String.format("%s - %d x %.2f (%.1f%% off) = %.2f\n", name, quantity, price, discount, total));
            grandTotal += total;
        }

        bill.append("\nGrand Total: ").append(String.format("%.2f", grandTotal));
        JOptionPane.showMessageDialog(this, bill.toString(), "Bill Generated", JOptionPane.INFORMATION_MESSAGE);

        // Clear the cart
        cartTableModel.setRowCount(0);
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
        Cashier cashier = new Cashier();
        cashier.inventory.add(new Product("Apple", 1.5, 100));
        cashier.inventory.add(new Product("Banana", 0.8, 150));
        cashier.inventory.add(new Product("Orange", 1.2, 120));

        SwingUtilities.invokeLater(cashier::setVisible);
    }
}
