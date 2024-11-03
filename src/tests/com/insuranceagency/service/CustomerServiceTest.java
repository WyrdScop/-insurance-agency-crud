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

/**
 * Test suite for the {@link CustomerService} class.
 * This class tests CRUD operations provided by {@link CustomerService},
 * including creation, retrieval, updating, and deletion of customer records.
 */
public class CustomerServiceTest {

    private final CustomerService customerService = new CustomerService();

    /**
     * Sets up the test database before each test by creating
     * a new 'customers' table with a fresh auto-increment ID.
     * Ensures each test has a clean, isolated environment.
     *
     * @throws Exception if there is an error setting up the database
     */
    @Before
    public void setUp() throws Exception {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS customers");
            stmt.executeUpdate(
                "CREATE TABLE customers (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(255), " +
                "email VARCHAR(255), " +
                "phone VARCHAR(20), " +
                "address VARCHAR(255), " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")"
            );
            stmt.executeUpdate("ALTER TABLE customers AUTO_INCREMENT = 1");
        }
    }

    /**
     * Cleans up the test database after each test by dropping
     * the 'customers' table to prevent data persistence across tests.
     *
     * @throws Exception if there is an error tearing down the database
     */
    @After
    public void tearDown() throws Exception {
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS customers");
        }
    }

    /**
     * Verifies that a {@link CustomerService} instance is successfully created.
     */
    @Test
    public void testCustomerServiceCreation() {
        assertNotNull("CustomerService instance should be created", customerService);
    }

    /**
     * Tests retrieval of a customer by their ID.
     * Verifies successful retrieval of an existing customer and that
     * attempting to retrieve a non-existent customer returns null.
     */
    @Test
    public void testGetCustomerById() {
        Customer testCustomer = new Customer(1, "Jane Doe", "jane@example.com", "555-6789", "456 Oak St", new Date());
        customerService.addCustomer(testCustomer);

        // Verify successful retrieval of existing customer
        Customer retrievedCustomer = customerService.getCustomerById(1);
        assertNotNull("Customer should be found", retrievedCustomer);
        assertEquals("Customer name should match", "Jane Doe", retrievedCustomer.getName());

        // Verify retrieval attempt of a non-existent customer
        Customer nonExistentCustomer = customerService.getCustomerById(999);
        assertNull("Customer should not be found", nonExistentCustomer);
    }

    /**
     * Tests deletion of a customer by their ID.
     * Verifies successful deletion of an existing customer and that
     * the customer cannot be retrieved after deletion.
     */
    @Test
    public void testDeleteCustomer() {
        Customer testCustomer = new Customer(2, "Mark Smith", "mark@example.com", "555-9876", "789 Pine St", new Date());
        customerService.addCustomer(testCustomer);

        // Verify successful deletion of customer
        boolean isDeleted = customerService.deleteCustomer(2);
        assertTrue("Customer should be deleted", isDeleted);
        assertNull("Customer should not exist after deletion", customerService.getCustomerById(2));
    }

    /**
     * Tests updating a customer's information.
     * Verifies that updating an existing customer's details (name) is successful
     * and that the updated information is correctly stored.
     */
    @Test
    public void testUpdateCustomer() {
        Customer testCustomer = new Customer(3, "Mike Davis", "mike@example.com", "555-1122", "123 Elm St", new Date());
        customerService.addCustomer(testCustomer);

        // Update and verify success
        testCustomer.setName("Michael Davis");
        boolean isUpdated = customerService.updateCustomer(testCustomer);
        assertTrue("Customer should be updated", isUpdated);

        // Verify updated information
        Customer updatedCustomer = customerService.getCustomerById(3);
        assertNotNull("Updated customer should be found", updatedCustomer);
        assertEquals("Updated name should match", "Michael Davis", updatedCustomer.getName());
    }
}