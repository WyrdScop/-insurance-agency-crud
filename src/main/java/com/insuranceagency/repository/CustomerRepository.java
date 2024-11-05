package main.java.com.insuranceagency.repository;

import java.sql.*;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.insuranceagency.Customer;
import main.java.com.insuranceagency.Database;

public class CustomerRepository {
    private static final Logger logger = Logger.getLogger(CustomerRepository.class.getName());

    public void addCustomer(Customer customer) {
        String query = "INSERT INTO customers (id, name, email, phone_number, address, date_of_birth) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customer.getId());
            pstmt.setString(2, customer.getName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPhoneNumber());
            pstmt.setString(5, customer.getAddress());
            pstmt.setDate(6, new java.sql.Date(customer.getDateOfBirth().getTime()));
            pstmt.executeUpdate();
            logger.info("Customer added successfully in repository.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding customer in repository: " + e.getMessage(), e);
        }
    }

    public Customer getCustomerById(int id) {
        String query = "SELECT * FROM customers WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                logger.info("Customer retrieved successfully in repository.");
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
            logger.log(Level.SEVERE, "Error retrieving customer in repository: " + e.getMessage(), e);
        }
        logger.warning(MessageFormat.format("Customer not found with ID: {0}", id));
        return null;
    }

    public boolean updateCustomer(Customer customer) {
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
                logger.info("Customer updated successfully in repository.");
                return true;
            } else {
                logger.warning(MessageFormat.format("No customer found with ID: {0}", customer.getId()));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating customer in repository: " + e.getMessage(), e);
        }
        return false;
    }

    public boolean deleteCustomer(int id) {
        String query = "DELETE FROM customers WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("Customer deleted successfully in repository.");
                return true;
            } else {
                logger.warning(MessageFormat.format("No customer found with ID: {0}", id));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting customer in repository: " + e.getMessage(), e);
        }
        return false;
    }
}