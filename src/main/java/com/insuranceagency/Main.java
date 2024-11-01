package main.java.com.insuranceagency;

public class Main {
    public static void main(String[] args) {
        CustomerService service = new CustomerService();

        // Example: Adding a customer
        Customer customer = new Customer(1, "John Doe", "john@example.com");
        service.addCustomer(customer);

        System.out.println("Customer added successfully.");
    }
}