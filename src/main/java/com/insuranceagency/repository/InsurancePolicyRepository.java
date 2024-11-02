package main.java.com.insuranceagency.repository;

import main.java.com.insuranceagency.InsurancePolicy;
import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import main.java.com.insuranceagency.Database;
public class InsurancePolicyRepository {
    private static final Logger logger = Logger.getLogger(InsurancePolicyRepository.class.getName());

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
            logger.info("Policy added to database: " + policy.getPolicyNumber());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding policy: " + e.getMessage(), e);
        }
    }

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
            logger.log(Level.SEVERE, "Error retrieving policy by ID: " + policyId, e);
        }
        logger.warning("Policy not found with ID: " + policyId);
        return null;
    }

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
            logger.log(Level.SEVERE, "Error retrieving policy by policy number: " + policyNumber, e);
        }
        logger.warning("Policy not found with policy number: " + policyNumber);
        return null;
    }

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

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating policy: " + policy.getPolicyNumber(), e);
        }
        return false;
    }

    public boolean deletePolicy(int policyId) {
        String query = "DELETE FROM insurance_policies WHERE policy_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, policyId);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting policy with ID: " + policyId, e);
        }
        return false;
    }
}