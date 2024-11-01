package main.java.com.insuranceagency;

public class Main {
    public static void main(String[] args) {
        CustomerService service = new CustomerService();

        // Test Add (Create) with Duplicate Check
        Customer newCustomer = new Customer(1, "John Doe", "john@example.com");
        if (service.getCustomerById(1) == null) {
            service.addCustomer(newCustomer);
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Customer with this ID already exists.");
        }

        // Test Retrieve (Read)
        Customer retrievedCustomer = service.getCustomerById(1);
        if (retrievedCustomer != null) {
            System.out.println("Retrieved Customer: " + retrievedCustomer.getName() + ", " + retrievedCustomer.getEmail());
        } else {
            System.out.println("Customer not found.");
        }

        // Test Update
        Customer updatedCustomer = new Customer(1, "John Doe Jr.", "john.jr@example.com");
        boolean isUpdated = service.updateCustomer(updatedCustomer);
        if (isUpdated) {
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Customer update failed.");
        }

        // Verify Update
        Customer verifyUpdatedCustomer = service.getCustomerById(1);
        if (verifyUpdatedCustomer != null) {
            System.out.println("Updated Customer: " + verifyUpdatedCustomer.getName() + ", " + verifyUpdatedCustomer.getEmail());
        }

        // Test Delete
        boolean isDeleted = service.deleteCustomer(1);
        if (isDeleted) {
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Customer delete failed.");
        }

        // Verify Deletion
        Customer verifyDeletedCustomer = service.getCustomerById(1);
        if (verifyDeletedCustomer == null) {
            System.out.println("Customer deletion verified; customer not found.");
        }
    }
}