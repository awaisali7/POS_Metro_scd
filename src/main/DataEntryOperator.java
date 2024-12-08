package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DataEntryOperator extends JFrame {
    private final List<Vendor> vendors = new ArrayList<>();
    private final DefaultTableModel vendorTableModel;
    private final DefaultTableModel productTableModel;

    public DataEntryOperator() {
        // Set frame properties
        setTitle("Data Entry Operator - Metro POS System");
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Title label
        JLabel titleLabel = new JLabel("Data Entry Operator Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(34, 139, 34)); // Dark green
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Tabs for managing vendors and products
        JTabbedPane tabbedPane = new JTabbedPane();

        // Vendor Management Tab
        vendorTableModel = new DefaultTableModel(new String[]{"Name", "Contact"}, 0);
        JTable vendorTable = new JTable(vendorTableModel);
        JScrollPane vendorScrollPane = new JScrollPane(vendorTable);
        JPanel vendorPanel = new JPanel(new BorderLayout());
        vendorPanel.add(vendorScrollPane, BorderLayout.CENTER);

        JButton addVendorButton = createButton("Add Vendor");
        addVendorButton.addActionListener(e -> showVendorManagementDialog());
        vendorPanel.add(addVendorButton, BorderLayout.SOUTH);

        tabbedPane.addTab("Vendors", vendorPanel);

        // Product Management Tab
        productTableModel = new DefaultTableModel(new String[]{"Name", "Category", "Original Price", "Sale Price"}, 0);
        JTable productTable = new JTable(productTableModel);
        JScrollPane productScrollPane = new JScrollPane(productTable);
        JPanel productPanel = new JPanel(new BorderLayout());
        productPanel.add(productScrollPane, BorderLayout.CENTER);

        JButton addProductButton = createButton("Add Product");
        addProductButton.addActionListener(e -> showProductManagementDialog());
        productPanel.add(addProductButton, BorderLayout.SOUTH);

        tabbedPane.addTab("Products", productPanel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void showVendorManagementDialog() {
        JDialog dialog = new JDialog(this, "Manage Vendors", true);
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField vendorNameField = new JTextField();
        JTextField vendorContactField = new JTextField();

        dialog.add(new JLabel("Vendor Name:"));
        dialog.add(vendorNameField);
        dialog.add(new JLabel("Vendor Contact:"));
        dialog.add(vendorContactField);

        JButton saveButton = createButton("Save");
        JButton cancelButton = createButton("Cancel");

        saveButton.addActionListener(e -> {
            String vendorName = vendorNameField.getText();
            String vendorContact = vendorContactField.getText();

            if (vendorName.isEmpty() || vendorContact.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                vendors.add(new Vendor(vendorName, vendorContact));
                vendorTableModel.addRow(new Object[]{vendorName, vendorContact});
                JOptionPane.showMessageDialog(dialog, "Vendor added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.add(saveButton);
        dialog.add(cancelButton);
        dialog.setVisible(true);
    }

    private void showProductManagementDialog() {
        JDialog dialog = new JDialog(this, "Manage Products", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));

        JTextField productNameField = new JTextField();
        JTextField productCategoryField = new JTextField();
        JTextField productOriginalPriceField = new JTextField();
        JTextField productSalePriceField = new JTextField();

        dialog.add(new JLabel("Product Name:"));
        dialog.add(productNameField);
        dialog.add(new JLabel("Category:"));
        dialog.add(productCategoryField);
        dialog.add(new JLabel("Original Price:"));
        dialog.add(productOriginalPriceField);
        dialog.add(new JLabel("Sale Price:"));
        dialog.add(productSalePriceField);

        JButton saveButton = createButton("Save");
        JButton cancelButton = createButton("Cancel");

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
                    productTableModel.addRow(new Object[]{productName, productCategory, originalPrice, salePrice});
                    JOptionPane.showMessageDialog(dialog, "Product saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid price values.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.add(saveButton);
        dialog.add(cancelButton);
        dialog.setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(34, 139, 34)); // Dark green
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(50, 205, 50)); // Lighter green
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(34, 139, 34)); // Dark green
            }
        });

        return button;
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
