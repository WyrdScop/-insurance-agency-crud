package main.java.com.insuranceagency;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import main.java.com.insuranceagency.service.CustomerService;
import main.java.com.insuranceagency.service.InsurancePolicyService;

public class ConsoleUI {
    private final CustomerService customerService;
    private final Scanner scanner = new Scanner(System.in);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // New constructor to inject services
    public ConsoleUI(CustomerService customerService, InsurancePolicyService policyService) {
        this.customerService = customerService;
    }
    public void start() {
        while (true) {
            try {
                System.out.println("\n--- Insurance Agency Management ---");
                System.out.println("1. Add Customer");
                System.out.println("2. View Customer");
                System.out.println("3. Update Customer");
                System.out.println("4. Delete Customer");
                System.out.println("5. Exit");

                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1 -> addCustomer();
                    case 2 -> viewCustomer();
                    case 3 -> updateCustomer();
                    case 4 -> deleteCustomer();
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();  // Clear invalid input
            }
        }
    }

    private void addCustomer() {
        try {
            System.out.print("Enter Customer ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Customer Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Customer Email: ");
            String email = scanner.nextLine();

            System.out.print("Enter Customer Phone: ");
            String phone = scanner.nextLine();

            System.out.print("Enter Customer Address: ");
            String address = scanner.nextLine();

            System.out.print("Enter Date of Birth (yyyy-MM-dd): ");
            Date dob = dateFormat.parse(scanner.nextLine());

            customerService.addCustomer(new Customer(id, name, email, phone, address, dob));
            System.out.println("Customer added successfully.");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private void viewCustomer() {
        try {
            System.out.print("Enter Customer ID to view: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Customer customer = customerService.getCustomerById(id);
            if (customer != null) {
                System.out.println("Customer Details: " + customer);
            } else {
                System.out.println("Customer not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid ID.");
            scanner.nextLine();  // Clear invalid input
        }
    }

    private void updateCustomer() {
        try {
            System.out.print("Enter Customer ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Customer customer = customerService.getCustomerById(id);
            if (customer != null) {
                System.out.print("Enter New Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter New Email: ");
                String email = scanner.nextLine();

                System.out.print("Enter New Phone: ");
                String phone = scanner.nextLine();

                System.out.print("Enter New Address: ");
                String address = scanner.nextLine();

                System.out.print("Enter New Date of Birth (yyyy-MM-dd): ");
                Date dob = dateFormat.parse(scanner.nextLine());

                customer.setName(name);
                customer.setEmail(email);
                customer.setPhoneNumber(phone);
                customer.setAddress(address);
                customer.setDateOfBirth(dob);

                customerService.updateCustomer(customer);
                System.out.println("Customer updated successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
            scanner.nextLine();  // Clear invalid input
        }
    }

    private void deleteCustomer() {
        try {
            System.out.print("Enter Customer ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            boolean isDeleted = customerService.deleteCustomer(id);
            if (isDeleted) {
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid ID.");
            scanner.nextLine();  // Clear invalid input
        }
    }
}