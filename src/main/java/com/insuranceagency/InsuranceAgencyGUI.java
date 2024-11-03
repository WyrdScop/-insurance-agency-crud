package main.java.com.insuranceagency;

import java.awt.*;
import java.util.Date;
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
        // Create a dialog for viewing a customer
        JDialog viewDialog = new JDialog(this, "View Customer", true);
        viewDialog.setSize(300, 200);
        viewDialog.setLayout(new GridLayout(3, 1));
    
        // Create input field for Customer ID
        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        inputPanel.add(new JLabel("Enter Customer ID:"));
        JTextField idField = new JTextField();
        inputPanel.add(idField);
    
        // Area to display customer details
        JTextArea customerInfoArea = new JTextArea();
        customerInfoArea.setEditable(false);
    
        // Add button to retrieve and display customer information
        JButton viewButton = new JButton("View Customer");
        viewButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Customer customer = customerService.getCustomerById(id);
    
                if (customer != null) {
                    customerInfoArea.setText(
                        "ID: " + customer.getId() + "\n" +
                        "Name: " + customer.getName() + "\n" +
                        "Email: " + customer.getEmail() + "\n" +
                        "Phone: " + customer.getPhoneNumber() + "\n" +
                        "Address: " + customer.getAddress() + "\n" +
                        "DOB: " + customer.getDateOfBirth()
                    );
                } else {
                    customerInfoArea.setText("Customer not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Add components to the dialog
        viewDialog.add(inputPanel);
        viewDialog.add(viewButton);
        viewDialog.add(new JScrollPane(customerInfoArea));
        viewDialog.setVisible(true);
    }

    private void showUpdateCustomerDialog() {
        // Create a dialog for updating customer information
        JDialog updateDialog = new JDialog(this, "Update Customer", true);
        updateDialog.setSize(400, 300);
        updateDialog.setLayout(new GridLayout(6, 2));
    
        // Input field for Customer ID to fetch details
        JPanel idPanel = new JPanel(new GridLayout(1, 2));
        idPanel.add(new JLabel("Enter Customer ID:"));
        JTextField idField = new JTextField();
        idPanel.add(idField);
    
        // Fields to update customer details
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField addressField = new JTextField();
    
        JButton fetchButton = new JButton("Fetch Customer");
        fetchButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Customer customer = customerService.getCustomerById(id);
    
                if (customer != null) {
                    // Populate fields with existing details
                    nameField.setText(customer.getName());
                    emailField.setText(customer.getEmail());
                    phoneField.setText(customer.getPhoneNumber());
                    addressField.setText(customer.getAddress());
                } else {
                    JOptionPane.showMessageDialog(this, "Customer not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Button to save updated information
        JButton updateButton = new JButton("Update Customer");
        updateButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Customer customer = customerService.getCustomerById(id);
    
                if (customer != null) {
                    customer.setName(nameField.getText());
                    customer.setEmail(emailField.getText());
                    customer.setPhoneNumber(phoneField.getText());
                    customer.setAddress(addressField.getText());
    
                    if (customerService.updateCustomer(customer)) {
                        JOptionPane.showMessageDialog(this, "Customer updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update customer.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Adding components to the dialog
        updateDialog.add(idPanel);
        updateDialog.add(fetchButton);
        updateDialog.add(new JLabel("Name:"));
        updateDialog.add(nameField);
        updateDialog.add(new JLabel("Email:"));
        updateDialog.add(emailField);
        updateDialog.add(new JLabel("Phone:"));
        updateDialog.add(phoneField);
        updateDialog.add(new JLabel("Address:"));
        updateDialog.add(addressField);
        updateDialog.add(new JLabel(""));  // Filler for alignment
        updateDialog.add(updateButton);
    
        updateDialog.setVisible(true);
    }

    private void showDeleteCustomerDialog() {
        // Create a dialog for deleting a customer
        JDialog deleteDialog = new JDialog(this, "Delete Customer", true);
        deleteDialog.setSize(300, 150);
        deleteDialog.setLayout(new GridLayout(3, 1));
    
        // Field to enter Customer ID
        JPanel idPanel = new JPanel(new GridLayout(1, 2));
        idPanel.add(new JLabel("Enter Customer ID:"));
        JTextField idField = new JTextField();
        idPanel.add(idField);
    
        // Delete button to confirm deletion
        JButton deleteButton = new JButton("Delete Customer");
        deleteButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                boolean isDeleted = customerService.deleteCustomer(id);
    
                if (isDeleted) {
                    JOptionPane.showMessageDialog(this, "Customer deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Customer not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        // Adding components to the dialog
        deleteDialog.add(idPanel);
        deleteDialog.add(deleteButton);
    
        deleteDialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InsuranceAgencyGUI gui = new InsuranceAgencyGUI();
            gui.setVisible(true);
        });
    }
}