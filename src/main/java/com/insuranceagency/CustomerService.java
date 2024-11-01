package main.java.com.insuranceagency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class CustomerService {

    private static final Logger logger = Logger.getLogger(CustomerService.class.getName());

    // Validation Method
    private boolean isValidCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().isEmpty()) {
            logger.warning("Validation Error: Name cannot be empty.");
            return false;
        }

        if (customer.getEmail() == null || !customer.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            logger.warning("Validation Error: Invalid email format.");
            return false;
        }

        if (customer.getPhoneNumber() == null || customer.getPhoneNumber().isEmpty()) {
            logger.warning("Validation Error: Phone number cannot be empty.");
            return false;
        }

        return true;
    }

    // Create
    public void addCustomer(Customer customer) {
        if (!isValidCustomer(customer)) return;

        String query = "INSERT INTO customers (id, name, email, phone_number, address, date_of_birth) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            logger.info("Attempting to add customer with ID: " + customer.getId());
            pstmt.setInt(1, customer.getId());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPhoneNumber());
            pstmt.setString(5, customer.getAddress());
            pstmt.setDate(6, new java.sql.Date(customer.getDateOfBirth().getTime()));
            pstmt.executeUpdate();
            logger.info("Customer added successfully.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding customer: " + e.getMessage(), e);
        }
    }

    // Read
    public Customer getCustomerById(int id) {
        String query = "SELECT * FROM customers WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            logger.info("Retrieving customer with ID: " + id);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                logger.info("Customer retrieved successfully.");
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
            logger.log(Level.SEVERE, "Error retrieving customer: " + e.getMessage(), e);
        }
        logger.warning("Customer not found with ID: " + id);
        return null;
    }

    // Update
    public boolean updateCustomer(Customer customer) {
        if (!isValidCustomer(customer)) return false;

        String query = "UPDATE customers SET name = ?, email = ?, phone_number = ?, address = ?, date_of_birth = ? WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            logger.info("Updating customer with ID: " + customer.getId());
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPhoneNumber());
            pstmt.setString(4, customer.getAddress());
            pstmt.setDate(5, new java.sql.Date(customer.getDateOfBirth().getTime()));
            pstmt.setInt(6, customer.getId());

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                logger.info("Customer updated successfully.");
                return true;
            } else {
                logger.warning("No customer found with ID: " + customer.getId());
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating customer: " + e.getMessage(), e);
        }
        return false;
    }

    // Delete
    public boolean deleteCustomer(int id) {
        String query = "DELETE FROM customers WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            logger.info("Deleting customer with ID: " + id);
            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("Customer deleted successfully.");
                return true;
            } else {
                logger.warning("No customer found with ID: " + id);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting customer: " + e.getMessage(), e);
        }
        return false;
    }
}