package main.java.com.insuranceagency.service;

import java.util.logging.Logger;
import main.java.com.insuranceagency.InsurancePolicy;
import main.java.com.insuranceagency.repository.InsurancePolicyRepository;

public class InsurancePolicyService {
    private static final Logger logger = Logger.getLogger(InsurancePolicyService.class.getName());
    private final InsurancePolicyRepository policyRepository;

    public InsurancePolicyService() {
        this.policyRepository = new InsurancePolicyRepository();
    }

    // Add Policy
    public void addPolicy(InsurancePolicy policy) {
        logger.info("Attempting to add policy with Policy Number: " + policy.getPolicyNumber());
        policyRepository.addPolicy(policy);
        logger.info("Policy added successfully.");
    }

    // Get Policy by ID
    public InsurancePolicy getPolicyById(int policyId) {
        logger.info("Retrieving policy with ID: " + policyId);
        return policyRepository.getPolicyById(policyId);
    }

    // Get Policy by Policy Number
    public InsurancePolicy getPolicyByPolicyNumber(String policyNumber) {
        logger.info("Retrieving policy with Policy Number: " + policyNumber);
        return policyRepository.getPolicyByPolicyNumber(policyNumber);
    }

    // Update Policy
    public boolean updatePolicy(InsurancePolicy policy) {
        logger.info("Updating policy with ID: " + policy.getPolicyId());
        return policyRepository.updatePolicy(policy);
    }

    // Delete Policy
    public boolean deletePolicy(int policyId) {
        logger.info("Deleting policy with ID: " + policyId);
        return policyRepository.deletePolicy(policyId);
    }
}