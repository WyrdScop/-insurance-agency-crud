package main.java.com.insuranceagency;

import main.java.com.insuranceagency.service.CustomerService;
import main.java.com.insuranceagency.service.InsurancePolicyService;

import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        InsurancePolicyService policyService = new InsurancePolicyService();

        // Instantiate the ConsoleUI class, which will handle user interaction.
        ConsoleUI consoleUI = new ConsoleUI(customerService, policyService);
        consoleUI.start();
    }
}