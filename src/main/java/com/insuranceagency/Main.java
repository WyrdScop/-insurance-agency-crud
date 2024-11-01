package main.java.com.insuranceagency;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        InsurancePolicyService policyService = new InsurancePolicyService();

        // Step 1: Add Customer (Create) with Duplicate Check
        Customer newCustomer = new Customer(1, "John Doe", "john@example.com", "555-1234", "123 Elm St", new Date());
        if (customerService.getCustomerById(newCustomer.getId()) == null) {
            customerService.addCustomer(newCustomer);
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Customer with this ID already exists.");
        }

        // Step 2: Retrieve Customer (Read)
        Customer retrievedCustomer = customerService.getCustomerById(newCustomer.getId());
        if (retrievedCustomer != null) {
            System.out.println("Retrieved Customer: " + retrievedCustomer.getName() + ", " + retrievedCustomer.getEmail());
        } else {
            System.out.println("Customer not found.");
        }

        // Step 3: Add a New Insurance Policy with Dynamic ID Handling
        InsurancePolicy newPolicy = new InsurancePolicy(0, newCustomer.getId(), "POL12345", "Auto", 5000.00, new Date(), new Date());
        policyService.addPolicy(newPolicy);
        System.out.println("Policy added successfully.");

        // Retrieve the newly added policy by policy number
        InsurancePolicy retrievedPolicy = policyService.getPolicyByPolicyNumber("POL12345");
        if (retrievedPolicy != null) {
            int policyId = retrievedPolicy.getPolicyId(); // Dynamically get the ID for future actions
            System.out.println("Retrieved Policy: " + retrievedPolicy.getPolicyNumber() + ", Type: " + retrievedPolicy.getPolicyType());

            // Step 4: Update the Insurance Policy
            retrievedPolicy.setPolicyType("Home"); // Modify policy type for the update test
            retrievedPolicy.setCoverageAmount(10000.00);
            boolean isPolicyUpdated = policyService.updatePolicy(retrievedPolicy);
            if (isPolicyUpdated) {
                System.out.println("Policy updated successfully.");
            } else {
                System.out.println("Policy update failed.");
            }

            // Step 5: Verify Policy Update
            InsurancePolicy verifyUpdatedPolicy = policyService.getPolicyById(policyId);
            if (verifyUpdatedPolicy != null) {
                System.out.println("Updated Policy: " + verifyUpdatedPolicy.getPolicyNumber() + ", Type: " + verifyUpdatedPolicy.getPolicyType() + ", Coverage: " + verifyUpdatedPolicy.getCoverageAmount());
            } else {
                System.out.println("Policy not found after update.");
            }

            // Step 6: Delete Insurance Policy
            boolean isPolicyDeleted = policyService.deletePolicy(policyId);
            if (isPolicyDeleted) {
                System.out.println("Policy deleted successfully.");
            } else {
                System.out.println("Policy delete failed.");
            }

            // Step 7: Verify Policy Deletion
            InsurancePolicy verifyDeletedPolicy = policyService.getPolicyById(policyId);
            if (verifyDeletedPolicy == null) {
                System.out.println("Policy deletion verified; policy not found.");
            }
        } else {
            System.out.println("Policy retrieval failed, skipping update and delete tests.");
        }

        // Step 8: Clean Up - Delete Customer for Re-testing
        boolean isCustomerDeleted = customerService.deleteCustomer(newCustomer.getId());
        if (isCustomerDeleted) {
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Customer delete failed.");
        }

        // Step 9: Verify Customer Deletion
        Customer verifyDeletedCustomer = customerService.getCustomerById(newCustomer.getId());
        if (verifyDeletedCustomer == null) {
            System.out.println("Customer deletion verified; customer not found.");
        }
    }
}