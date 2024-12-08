package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class DataEntryOperator extends JFrame {
    private final List<Vendor> vendors = new ArrayList<>();

    public DataEntryOperator() {
        // Set frame properties
        setTitle("Data Entry Operator - Metro POS System");
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Add title label
        JLabel titleLabel = new JLabel("Data Entry Operator Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 139, 34)); // Dark green
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add buttons
        JButton manageVendorsButton = new JButton("Manage Vendors");
        JButton manageProductsButton = new JButton("Manage Products");
        buttonPanel.add(manageVendorsButton);
        buttonPanel.add(manageProductsButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Action listeners
        manageVendorsButton.addActionListener(e -> showVendorManagementDialog());
        manageProductsButton.addActionListener(e -> showProductManagementDialog());
    }

    private void showVendorManagementDialog() {
        // Dialog for managing vendors
        JDialog dialog = new JDialog(this, "Manage Vendors", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));

        // Form fields
        JTextField vendorNameField = new JTextField();
        JTextField vendorContactField = new JTextField();

        // Add components
        dialog.add(new JLabel("Vendor Name:"));
        dialog.add(vendorNameField);
        dialog.add(new JLabel("Vendor Contact:"));
        dialog.add(vendorContactField);

        // Buttons
        JButton saveButton = new JButton("Save Vendor");
        JButton cancelButton = new JButton("Cancel");
        dialog.add(saveButton);
        dialog.add(cancelButton);

        // Save button action
        saveButton.addActionListener(e -> {
            String vendorName = vendorNameField.getText();
            String vendorContact = vendorContactField.getText();

            if (vendorName.isEmpty() || vendorContact.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                vendors.add(new Vendor(vendorName, vendorContact));
                JOptionPane.showMessageDialog(dialog, "Vendor added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            }
        });

        // Cancel button action
        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void showProductManagementDialog() {
        // Dialog for managing products
        JDialog dialog = new JDialog(this, "Manage Products", true);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(6, 2, 10, 10));

        // Form fields
        JTextField productNameField = new JTextField();
        JTextField productCategoryField = new JTextField();
        JTextField productOriginalPriceField = new JTextField();
        JTextField productSalePriceField = new JTextField();

        // Add components
        dialog.add(new JLabel("Product Name:"));
        dialog.add(productNameField);
        dialog.add(new JLabel("Category:"));
        dialog.add(productCategoryField);
        dialog.add(new JLabel("Original Price:"));
        dialog.add(productOriginalPriceField);
        dialog.add(new JLabel("Sale Price:"));
        dialog.add(productSalePriceField);

        // Buttons
        JButton saveButton = new JButton("Save Product");
        JButton cancelButton = new JButton("Cancel");
        dialog.add(saveButton);
        dialog.add(cancelButton);

        // Save button action
        saveButton.addActionListener(e -> {
            String productName = productNameField.getText();
            String productCategory = productCategoryField.getText();
            String originalPriceText = productOriginalPriceField.getText();
            String salePriceText = productSalePriceField.getText();

            if (productName.isEmpty() || productCategory.isEmpty() || originalPriceText.isEmpty() || salePriceText.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    double originalPrice = Double.parseDouble(originalPriceText);
                    double salePrice = Double.parseDouble(salePriceText);
                    JOptionPane.showMessageDialog(dialog, "Product saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid price values.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Cancel button action
        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataEntryOperator operator = new DataEntryOperator();
            operator.setVisible(true);
        });
    }
}

// Helper class to represent a Vendor
class Vendor {
    private final String name;
    private final String contact;

    public Vendor(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Contact: %s", name, contact);
    }
}
