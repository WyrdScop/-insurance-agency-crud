package com.insuranceagency.service;

import main.java.com.insuranceagency.Customer;
import main.java.com.insuranceagency.service.CustomerService;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

public class CustomerServiceTest {

    private final CustomerService customerService = new CustomerService();

    @Test
    public void testCustomerServiceCreation() {
        assertNotNull("CustomerService instance should be created", customerService);
    }

    @Test
    public void testGetCustomerById() {
        // Arrange
        Customer testCustomer = new Customer(2, "Jane Doe", "jane@example.com", "555-6789", "456 Oak St", new Date());
        customerService.addCustomer(testCustomer);

        // Act - Success Case
        Customer retrievedCustomer = customerService.getCustomerById(2);

        // Assert - Success Case
        assertNotNull("Customer should be found", retrievedCustomer);
        assertEquals("Customer name should match", "Jane Doe", retrievedCustomer.getName());

        // Act - Failure Case
        Customer nonExistentCustomer = customerService.getCustomerById(999);

        // Assert - Failure Case
        assertNull("Customer should not be found", nonExistentCustomer);
    }
}