package main.java.com.insuranceagency;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        InsurancePolicyService policyService = new InsurancePolicyService();

        // Test Add (Create) Customer with Duplicate Check
        Customer newCustomer = new Customer(1, "John Doe", "john@example.com", "555-1234", "123 Elm St", new Date());
        if (customerService.getCustomerById(1) == null) {
            customerService.addCustomer(newCustomer);
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Customer with this ID already exists.");
        }

        // Test Retrieve (Read) Customer
        Customer retrievedCustomer = customerService.getCustomerById(1);
        if (retrievedCustomer != null) {
            System.out.println("Retrieved Customer: " + retrievedCustomer.getName() + ", " + retrievedCustomer.getEmail());
        } else {
            System.out.println("Customer not found.");
        }

        // Test Add (Create) Insurance Policy for Customer
        InsurancePolicy newPolicy = new InsurancePolicy(0, 1, "POL12345", "Auto", 5000.00, new Date(), new Date());
        policyService.addPolicy(newPolicy);
        System.out.println("Policy added successfully.");

        // Test Retrieve (Read) Insurance Policy by ID
        InsurancePolicy retrievedPolicy = policyService.getPolicyById(1);
        if (retrievedPolicy != null) {
            System.out.println("Retrieved Policy: " + retrievedPolicy.getPolicyNumber() + ", " + retrievedPolicy.getPolicyType() + ", Coverage: " + retrievedPolicy.getCoverageAmount());
        } else {
            System.out.println("Policy not found.");
        }

        // Test Update Insurance Policy
        InsurancePolicy updatedPolicy = new InsurancePolicy(1, 1, "POL12345", "Home", 10000.00, new Date(), new Date());
        boolean isPolicyUpdated = policyService.updatePolicy(updatedPolicy);
        if (isPolicyUpdated) {
            System.out.println("Policy updated successfully.");
        } else {
            System.out.println("Policy update failed.");
        }

        // Verify Update for Insurance Policy
        InsurancePolicy verifyUpdatedPolicy = policyService.getPolicyById(1);
        if (verifyUpdatedPolicy != null) {
            System.out.println("Updated Policy: " + verifyUpdatedPolicy.getPolicyNumber() + ", " + verifyUpdatedPolicy.getPolicyType() + ", Coverage: " + verifyUpdatedPolicy.getCoverageAmount());
        }

        // Test Delete Insurance Policy
        boolean isPolicyDeleted = policyService.deletePolicy(1);
        if (isPolicyDeleted) {
            System.out.println("Policy deleted successfully.");
        } else {
            System.out.println("Policy delete failed.");
        }

        // Verify Deletion of Insurance Policy
        InsurancePolicy verifyDeletedPolicy = policyService.getPolicyById(1);
        if (verifyDeletedPolicy == null) {
            System.out.println("Policy deletion verified; policy not found.");
        }

        // Cleanup - Delete Customer for re-testing
        boolean isCustomerDeleted = customerService.deleteCustomer(1);
        if (isCustomerDeleted) {
            System.out.println("Customer deleted successfully.");
        }
    }
}