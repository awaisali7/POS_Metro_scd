package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JComboBox<String> roleDropdown;

    public Login() {
        // Set frame properties
        setTitle("Login - Metro POS System");
        setSize(400, 400);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add picture
        JLabel pictureLabel = new JLabel(new ImageIcon(getClass().getResource("/assets/metro.jpeg")));
// Replace "logo.png" with your image file path
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(pictureLabel, gbc);

        // Add title label
        JLabel titleLabel = new JLabel("Metro POS System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 139, 34)); // Dark green
        gbc.gridy = 1;
        mainPanel.add(titleLabel, gbc);

        // Add username label and field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(usernameField, gbc);

        // Add password label and field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(passwordField, gbc);

        // Add role dropdown
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(roleLabel, gbc);

        roleDropdown = new JComboBox<>(new String[]{"Select Role", "SuperAdmin", "BranchManager", "DataEntryOperator", "Cashier"});
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(roleDropdown, gbc);

        // Add login button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(34, 139, 34)); // Dark green
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);

        // Add exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 6;
        mainPanel.add(exitButton, gbc);

        // Add action listeners
        loginButton.addActionListener(new LoginButtonListener());
        exitButton.addActionListener(e -> System.exit(0));

        // Add main panel to frame
        add(mainPanel);
    }

    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String selectedRole = (String) roleDropdown.getSelectedItem();

            if (selectedRole == null || selectedRole.equals("Select Role")) {
                JOptionPane.showMessageDialog(Login.this, "Please select a role", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validate credentials and role
            String role = getRole(username, password);
            if (role != null && role.equals(selectedRole)) {
                JOptionPane.showMessageDialog(Login.this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close login window
                new Dashboard(role).setVisible(true); // Pass role to Dashboard
            } else {
                JOptionPane.showMessageDialog(Login.this, "Invalid username, password, or role", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Extracted method to determine user role
    private String getRole(String username, String password) {
        if ("admin".equals(username) && "password".equals(password)) {
            return "SuperAdmin";
        } else if ("manager".equals(username) && "password".equals(password)) {
            return "BranchManager";
        } else if ("operator".equals(username) && "password".equals(password)) {
            return "DataEntryOperator";
        } else if ("cashier".equals(username) && "password".equals(password)) {
            return "Cashier";
        } else {
            return null;
        }
    }

    // Main method for testing the login screen
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.setVisible(true);
        });
    }
}
