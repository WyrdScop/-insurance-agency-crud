package com.insuranceagency.service;

import main.java.com.insuranceagency.Customer;
import main.java.com.insuranceagency.Database;
import main.java.com.insuranceagency.service.CustomerService;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;

import static org.junit.Assert.*;

public class CustomerServiceTest {

    private final CustomerService customerService = new CustomerService();

    @Before
    public void setUp() throws Exception {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM customers"); // Clear data before each test
        }
    }

    @After
    public void tearDown() throws Exception {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM customers"); // Clear data after each test
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
    public void testDeleteCustomer() {
        // Arrange
        Customer testCustomer = new Customer(4, "Mark Smith", "mark@example.com", "555-9876", "789 Pine St", new Date());
        customerService.addCustomer(testCustomer);

        // Act - Delete Customer
        boolean isDeleted = customerService.deleteCustomer(4);

        // Assert
        assertTrue("Customer should be deleted", isDeleted);
        assertNull("Customer should not exist after deletion", customerService.getCustomerById(4));
    }

    @Test
    public void testUpdateCustomer() {
        // Arrange
        Customer testCustomer = new Customer(3, "Mike Davis", "mike@example.com", "555-1122", "123 Elm St", new Date());
        customerService.addCustomer(testCustomer);

        // Act - Update Customer
        testCustomer.setName("Michael Davis");
        boolean isUpdated = customerService.updateCustomer(testCustomer);

        // Assert - Update Successful
        assertTrue("Customer should be updated", isUpdated);
        Customer updatedCustomer = customerService.getCustomerById(3);
        assertNotNull("Updated customer should be found", updatedCustomer);
        assertEquals("Updated name should match", "Michael Davis", updatedCustomer.getName());
    }
}