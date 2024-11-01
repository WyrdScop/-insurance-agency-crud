package main.java.com.insuranceagency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class InsurancePolicyService {

    // Create
    public void addPolicy(InsurancePolicy policy) {
        String query = "INSERT INTO insurance_policies (customer_id, policy_number, policy_type, coverage_amount, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, policy.getCustomerId());
            pstmt.setString(2, policy.getPolicyNumber());
            pstmt.setString(3, policy.getPolicyType());
            pstmt.setDouble(4, policy.getCoverageAmount());
            pstmt.setDate(5, new java.sql.Date(policy.getStartDate().getTime()));
            pstmt.setDate(6, new java.sql.Date(policy.getEndDate().getTime()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read by Policy ID
    public InsurancePolicy getPolicyById(int policyId) {
        String query = "SELECT * FROM insurance_policies WHERE policy_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, policyId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new InsurancePolicy(
                    rs.getInt("policy_id"),
                    rs.getInt("customer_id"),
                    rs.getString("policy_number"),
                    rs.getString("policy_type"),
                    rs.getDouble("coverage_amount"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Read by Policy Number (newly added method)
    public InsurancePolicy getPolicyByPolicyNumber(String policyNumber) {
        String query = "SELECT * FROM insurance_policies WHERE policy_number = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, policyNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new InsurancePolicy(
                    rs.getInt("policy_id"),
                    rs.getInt("customer_id"),
                    rs.getString("policy_number"),
                    rs.getString("policy_type"),
                    rs.getDouble("coverage_amount"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving policy by policy number: " + e.getMessage());
            e.printStackTrace();
        }
        System.err.println("Policy not found with policy number: " + policyNumber);
        return null;
    }

    // Update
    public boolean updatePolicy(InsurancePolicy policy) {
        String query = "UPDATE insurance_policies SET policy_number = ?, policy_type = ?, coverage_amount = ?, start_date = ?, end_date = ? WHERE policy_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, policy.getPolicyNumber());
            pstmt.setString(2, policy.getPolicyType());
            pstmt.setDouble(3, policy.getCoverageAmount());
            pstmt.setDate(4, new java.sql.Date(policy.getStartDate().getTime()));
            pstmt.setDate(5, new java.sql.Date(policy.getEndDate().getTime()));
            pstmt.setInt(6, policy.getPolicyId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete
    public boolean deletePolicy(int policyId) {
        String query = "DELETE FROM insurance_policies WHERE policy_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, policyId);

            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}