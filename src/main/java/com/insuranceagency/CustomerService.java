package main.java.com.insuranceagency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerService {
    
    // Create
    public void addCustomer(Customer customer) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read (Retrieve a customer by ID)
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
            e.printStackTrace();
        }
        return null; // Return null if customer not found
    }

    // Update (Modify customer details)
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
            return rowsUpdated > 0; // Returns true if update was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete (Remove customer by ID)
    public boolean deleteCustomer(int id) {
        String query = "DELETE FROM customers WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0; // Returns true if delete was successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}