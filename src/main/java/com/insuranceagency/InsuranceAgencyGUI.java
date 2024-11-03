package main.java.com.insuranceagency;

import java.awt.*;
import javax.swing.*;
import main.java.com.insuranceagency.service.CustomerService;

public class InsuranceAgencyGUI extends JFrame {
    private final CustomerService customerService = new CustomerService();

    public InsuranceAgencyGUI() {
        setTitle("Insurance Agency Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // Initialize Buttons for CRUD operations
        JButton addCustomerButton = new JButton("Add Customer");
        JButton viewCustomerButton = new JButton("View Customer");
        JButton updateCustomerButton = new JButton("Update Customer");
        JButton deleteCustomerButton = new JButton("Delete Customer");

        // Add action listeners to each button
        addCustomerButton.addActionListener(e -> showAddCustomerDialog());
        viewCustomerButton.addActionListener(e -> showViewCustomerDialog());
        updateCustomerButton.addActionListener(e -> showUpdateCustomerDialog());
        deleteCustomerButton.addActionListener(e -> showDeleteCustomerDialog());

        // Add buttons to the frame
        add(addCustomerButton);
        add(viewCustomerButton);
        add(updateCustomerButton);
        add(deleteCustomerButton);
    }

    // Define dialogs for CRUD operations (initially placeholders)
    private void showAddCustomerDialog() {
        JOptionPane.showMessageDialog(this, "Add Customer Dialog");
    }

    private void showViewCustomerDialog() {
        JOptionPane.showMessageDialog(this, "View Customer Dialog");
    }

    private void showUpdateCustomerDialog() {
        JOptionPane.showMessageDialog(this, "Update Customer Dialog");
    }

    private void showDeleteCustomerDialog() {
        JOptionPane.showMessageDialog(this, "Delete Customer Dialog");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InsuranceAgencyGUI gui = new InsuranceAgencyGUI();
            gui.setVisible(true);
        });
    }
}