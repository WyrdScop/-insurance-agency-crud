package com.insuranceagency.service;

import main.java.com.insuranceagency.Customer;
import main.java.com.insuranceagency.Database;
import main.java.com.insuranceagency.service.CustomerService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.Date;

public class CustomerServiceTest {

    private final CustomerService customerService = new CustomerService();

     @Before
    public void setUp() throws Exception {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM customers");
        }
    }

    @After
    public void tearDown() throws Exception {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM customers");
        }
    }

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

    @Test
    public void testUpdateCustomer() {
        // Arrange
        Customer testCustomer = new Customer(3, "Mark Spencer", "mark@example.com", "555-9876", "789 Maple St", new Date());
        customerService.addCustomer(testCustomer);

        // Act - Modify customer details
        testCustomer.setName("Marcus Spencer");
        testCustomer.setEmail("marcus@example.com");
        boolean isUpdated = customerService.updateCustomer(testCustomer);

        // Assert
        assertTrue("Customer should be updated successfully", isUpdated);
        Customer updatedCustomer = customerService.getCustomerById(3);
        assertEquals("Customer name should be updated", "Marcus Spencer", updatedCustomer.getName());
        assertEquals("Customer email should be updated", "marcus@example.com", updatedCustomer.getEmail());
    }
}