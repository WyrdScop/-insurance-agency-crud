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
        // Create dialog for adding a customer
        JDialog addDialog = new JDialog(this, "Add Customer", true);
        addDialog.setSize(300, 300);
        addDialog.setLayout(new GridLayout(6, 2));
    
        // Create input fields
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField addressField = new JTextField();
    
        // Add labels and fields to the dialog
        addDialog.add(new JLabel("ID:"));
        addDialog.add(idField);
        addDialog.add(new JLabel("Name:"));
        addDialog.add(nameField);
        addDialog.add(new JLabel("Email:"));
        addDialog.add(emailField);
        addDialog.add(new JLabel("Phone:"));
        addDialog.add(phoneField);
        addDialog.add(new JLabel("Address:"));
        addDialog.add(addressField);
    
        // Add 'Add Customer' button
        JButton submitButton = new JButton("Add Customer");
        submitButton.addActionListener(e -> {
            try {
                // Retrieve user inputs
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String address = addressField.getText();
    
                // Create new Customer and add it to the service
                customerService.addCustomer(new Customer(id, name, email, phone, address, new Date())); // Assuming Date() as placeholder for DOB
                JOptionPane.showMessageDialog(this, "Customer added successfully!");
                addDialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Add the submit button to the dialog
        addDialog.add(submitButton);
        addDialog.setVisible(true);
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