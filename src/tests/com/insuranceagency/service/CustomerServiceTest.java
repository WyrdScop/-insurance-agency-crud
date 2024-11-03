package com.insuranceagency.service;

import main.java.com.insuranceagency.service.CustomerService;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class CustomerServiceTest {
    
    @Test
    public void testCustomerServiceCreation() {
        CustomerService customerService = new CustomerService();
        assertNotNull("CustomerService instance should be created", customerService);
    }
}