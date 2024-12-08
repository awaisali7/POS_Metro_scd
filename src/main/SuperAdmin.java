package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SuperAdmin extends JFrame {
    private final List<Branch> branches = new ArrayList<>();

    public SuperAdmin() {
        // Set frame properties
        setTitle("Super Admin - Metro POS System");
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Add title label
        JLabel titleLabel = new JLabel("Super Admin Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 139, 34)); // Dark green
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add buttons
        JButton addBranchButton = new JButton("Add Branch");
        JButton viewBranchesButton = new JButton("View Branches");
        buttonPanel.add(addBranchButton);
        buttonPanel.add(viewBranchesButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Action listeners
        addBranchButton.addActionListener(e -> showAddBranchDialog());
        viewBranchesButton.addActionListener(e -> showBranchesList());
    }

    private void showAddBranchDialog() {
        // Dialog for adding a branch
        JDialog dialog = new JDialog(this, "Add Branch", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));

        // Form fields
        JTextField branchCodeField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField cityField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField phoneField = new JTextField();

        // Add components
        dialog.add(new JLabel("Branch Code:"));
        dialog.add(branchCodeField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("City:"));
        dialog.add(cityField);
        dialog.add(new JLabel("Address:"));
        dialog.add(addressField);
        dialog.add(new JLabel("Phone:"));
        dialog.add(phoneField);

        // Buttons
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        dialog.add(saveButton);
        dialog.add(cancelButton);

        // Save button action
        saveButton.addActionListener(e -> {
            String branchCode = branchCodeField.getText();
            String name = nameField.getText();
            String city = cityField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();

            if (branchCode.isEmpty() || name.isEmpty() || city.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                branches.add(new Branch(branchCode, name, city, address, phone));
                JOptionPane.showMessageDialog(dialog, "Branch added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            }
        });

        // Cancel button action
        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void showBranchesList() {
        // Display list of branches
        StringBuilder branchInfo = new StringBuilder("Branches:\n\n");
        for (Branch branch : branches) {
            branchInfo.append(branch).append("\n");
        }

        if (branches.isEmpty()) {
            branchInfo.append("No branches added yet.");
        }

        JOptionPane.showMessageDialog(this, branchInfo.toString(), "Branch List", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SuperAdmin admin = new SuperAdmin();
            admin.setVisible(true);
        });
    }
}

// Helper class to represent a Branch
class Branch {
    private final String branchCode;
    private final String name;
    private final String city;
    private final String address;
    private final String phone;

    public Branch(String branchCode, String name, String city, String address, String phone) {
        this.branchCode = branchCode;
        this.name = name;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("Code: %s, Name: %s, City: %s, Address: %s, Phone: %s",
                branchCode, name, city, address, phone);
    }
}
