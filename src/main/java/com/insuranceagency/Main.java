package main.java.com.insuranceagency;

import main.java.com.insuranceagency.service.CustomerService;
import main.java.com.insuranceagency.service.InsurancePolicyService;

import java.util.Date;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        InsurancePolicyService policyService = new InsurancePolicyService();

        // Step 1: Add Customer (Create) with Duplicate Check
        Customer newCustomer = new Customer(1, "John Doe", "john@example.com", "555-1234", "123 Elm St", new Date());
        if (customerService.getCustomerById(newCustomer.getId()) == null) {
            customerService.addCustomer(newCustomer);
            logger.info("Customer added successfully.");
        } else {
            logger.warning("Customer with this ID already exists.");
        }

        // Step 2: Retrieve Customer (Read)
        Customer retrievedCustomer = customerService.getCustomerById(newCustomer.getId());
        if (retrievedCustomer != null) {
            logger.info("Retrieved Customer: " + retrievedCustomer.getName() + ", " + retrievedCustomer.getEmail());
        } else {
            logger.warning("Customer not found.");
        }

        // Step 3: Add a New Insurance Policy with Dynamic ID Handling
        InsurancePolicy newPolicy = new InsurancePolicy(0, newCustomer.getId(), "POL12345", "Auto", 5000.00, new Date(), new Date());
        policyService.addPolicy(newPolicy);
        logger.info("Policy added successfully.");

        // Retrieve the newly added policy by policy number
        InsurancePolicy retrievedPolicy = policyService.getPolicyByPolicyNumber("POL12345");
        if (retrievedPolicy != null) {
            int policyId = retrievedPolicy.getPolicyId(); // Dynamically get the ID for future actions
            logger.info("Retrieved Policy: " + retrievedPolicy.getPolicyNumber() + ", Type: " + retrievedPolicy.getPolicyType());

            // Step 4: Update the Insurance Policy
            retrievedPolicy.setPolicyType("Home"); // Modify policy type for the update test
            retrievedPolicy.setCoverageAmount(10000.00);
            boolean isPolicyUpdated = policyService.updatePolicy(retrievedPolicy);
            if (isPolicyUpdated) {
                logger.info("Policy updated successfully.");
            } else {
                logger.warning("Policy update failed.");
            }

            // Step 5: Verify Policy Update
            InsurancePolicy verifyUpdatedPolicy = policyService.getPolicyById(policyId);
            if (verifyUpdatedPolicy != null) {
                logger.info("Updated Policy: " + verifyUpdatedPolicy.getPolicyNumber() + ", Type: " + verifyUpdatedPolicy.getPolicyType() + ", Coverage: " + verifyUpdatedPolicy.getCoverageAmount());
            } else {
                logger.warning("Policy not found after update.");
            }

            // Step 6: Delete Insurance Policy
            boolean isPolicyDeleted = policyService.deletePolicy(policyId);
            if (isPolicyDeleted) {
                logger.info("Policy deleted successfully.");
            } else {
                logger.warning("Policy delete failed.");
            }

            // Step 7: Verify Policy Deletion
            InsurancePolicy verifyDeletedPolicy = policyService.getPolicyById(policyId);
            if (verifyDeletedPolicy == null) {
                logger.info("Policy deletion verified; policy not found.");
            }
        } else {
            logger.warning("Policy retrieval failed, skipping update and delete tests.");
        }

        // Step 8: Clean Up - Delete Customer for Re-testing
        boolean isCustomerDeleted = customerService.deleteCustomer(newCustomer.getId());
        if (isCustomerDeleted) {
            logger.info("Customer deleted successfully.");
        } else {
            logger.warning("Customer delete failed.");
        }

        // Step 9: Verify Customer Deletion
        Customer verifyDeletedCustomer = customerService.getCustomerById(newCustomer.getId());
        if (verifyDeletedCustomer == null) {
            logger.info("Customer deletion verified; customer not found.");
        }
    }
}