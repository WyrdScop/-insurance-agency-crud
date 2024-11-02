package main.java.com.insuranceagency.service;

import java.util.logging.Logger;
import main.java.com.insuranceagency.Customer;
import main.java.com.insuranceagency.repository.CustomerRepository;

public class CustomerService {

    private static final Logger logger = Logger.getLogger(CustomerService.class.getName());
    private final CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

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

    public void addCustomer(Customer customer) {
        if (isValidCustomer(customer)) {
            customerRepository.addCustomer(customer); // Call repository for database operation
            logger.info("Customer added successfully.");
        }
    }

    public Customer getCustomerById(int id) {
        logger.info("Retrieving customer with ID: " + id);
        return customerRepository.getCustomerById(id); // Call repository for database retrieval
    }

    public boolean updateCustomer(Customer customer) {
        if (isValidCustomer(customer)) {
            boolean isUpdated = customerRepository.updateCustomer(customer); // Call repository for update
            if (isUpdated) {
                logger.info("Customer updated successfully.");
                return true;
            } else {
                logger.warning("Update failed; customer not found.");
            }
        }
        return false;
    }

    public boolean deleteCustomer(int id) {
        logger.info("Deleting customer with ID: " + id);
        boolean isDeleted = customerRepository.deleteCustomer(id); // Call repository for deletion
        if (isDeleted) {
            logger.info("Customer deleted successfully.");
            return true;
        } else {
            logger.warning("Delete failed; customer not found.");
        }
        return false;
    }
}