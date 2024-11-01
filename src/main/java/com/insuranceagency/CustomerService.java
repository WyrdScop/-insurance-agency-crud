package main.java.com.insuranceagency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerService {
    
    // Validation Method
    // Add this at the top, before any CRUD methods.
    private boolean isValidCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().isEmpty()) {
            System.err.println("Validation Error: Name cannot be empty.");
            return false;
        }

        if (customer.getEmail() == null || !customer.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            System.err.println("Validation Error: Invalid email format.");
            return false;
        }

        if (customer.getPhoneNumber() == null || customer.getPhoneNumber().isEmpty()) {
            System.err.println("Validation Error: Phone number cannot be empty.");
            return false;
        }

        return true;  // Return true if all validations pass
    }

    // Create
    // Add this method in place of the previous addCustomer method.
    public void addCustomer(Customer customer) {
        if (!isValidCustomer(customer)) return;  // Validate customer data first

        String query = "INSERT INTO customers (id, name, email, phone_number, address, date_of_birth) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customer.getId());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPhoneNumber());
            pstmt.setString(5, customer.getAddress());
            pstmt.setDate(6, new java.sql.Date(customer.getDateOfBirth().getTime())); // Convert java.util.Date to java.sql.Date
            pstmt.executeUpdate();
            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding customer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Read (Retrieve a customer by ID)
    // Replace the existing getCustomerById method with this one.
    public Customer getCustomerById(int id) {
        String query = "SELECT * FROM customers WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone_number"),
                    rs.getString("address"),
                    rs.getDate("date_of_birth")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving customer: " + e.getMessage());
            e.printStackTrace();
        }
        System.err.println("Customer not found with ID: " + id);
        return null; // Return null if customer not found
    }

    // Update (Modify customer details)
    // Replace your existing updateCustomer method with this one.
    public boolean updateCustomer(Customer customer) {
        if (!isValidCustomer(customer)) return false;  // Validate customer data first

        String query = "UPDATE customers SET name = ?, email = ?, phone_number = ?, address = ?, date_of_birth = ? WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPhoneNumber());
            pstmt.setString(4, customer.getAddress());
            pstmt.setDate(5, new java.sql.Date(customer.getDateOfBirth().getTime()));
            pstmt.setInt(6, customer.getId());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer updated successfully.");
                return true;
            } else {
                System.err.println("No customer found with ID: " + customer.getId());
            }
        } catch (SQLException e) {
            System.err.println("Error updating customer: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Delete (Remove customer by ID)
    // Replace the existing deleteCustomer method with this one.
    public boolean deleteCustomer(int id) {
        String query = "DELETE FROM customers WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Customer deleted successfully.");
                return true;
            } else {
                System.err.println("No customer found with ID: " + id);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}