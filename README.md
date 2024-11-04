# Insurance Agency CRUD Application

This Java-based CRUD application is built to manage customer records and insurance policies for an insurance agency. The application uses MySQL for data storage and JDBC for database connectivity. It includes features for database creation, robust validation, error handling, and an interactive user interface, with future scalability in mind.

## Features

- **Create**: Add new customer records and insurance policies to the database with complete validation.
- **Read**: Retrieve and view customer details and associated policy information.
- **Update**: Modify existing customer records and update policy details.
- **Delete**: Remove customers and their related insurance policies from the database.

## Technologies Used

- **Java**
- **MySQL** with **MySQL Connector/J** for JDBC connectivity
- **Swing** for graphical user interface
- **Logging** with Java’s built-in logging library for tracking application events
- **JUnit** for testing

## Setup Instructions

### Requirements

1. **Java** (JDK 11+ recommended)
2. **MySQL** (Ensure it is installed, configured, and running)

### Database Setup

1. **Create the Database**:

   - Open MySQL and create a database named `insurance_agency_db`.
   - Create the following tables:

     ```sql
     CREATE TABLE customers (
         id INT PRIMARY KEY,
         name VARCHAR(100),
         email VARCHAR(100),
         phone_number VARCHAR(15),
         address VARCHAR(255),
         date_of_birth DATE
     );

     CREATE TABLE insurance_policies (
         policy_id INT AUTO_INCREMENT PRIMARY KEY,
         customer_id INT,
         policy_number VARCHAR(50) NOT NULL,
         policy_type VARCHAR(50),
         coverage_amount DECIMAL(10, 2),
         start_date DATE,
         end_date DATE,
         FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
     );
     ```

2. **Configure Database Connection**:

   - In the `src/main/resources` directory, create a `db.properties` file (make sure it's not tracked by Git for security).
   - Add your database configuration:

     ```properties
     db.url=jdbc:mysql://localhost:3306/insurance_agency_db
     db.username=your_username
     db.password=your_password
     ```

3. **Compile the Application**:

   ```bash
   javac -d out -sourcepath src/main/java $(find src/main/java -name "*.java")
   ```

### Run The Application

java -cp "out:lib/\*" main.java.com.insuranceagency.Main

---

### **Running the GUI**

````markdown
### Running the GUI

Launch the GUI application with:

````bash
java -cp "out:lib/*" main.java.com.insuranceagency.InsuranceAgencyGUI

---

## Feature Descriptions

1. **Complete CRUD Functionality**:

   - Implemented methods for creating, reading, updating, and deleting customer records.
   - `CustomerService` class includes methods like `addCustomer`, `getCustomerById`, `updateCustomer`, and `deleteCustomer`.
   - GUI provides buttons and forms for each CRUD operation.

2. **Enhanced Database Design**:

   - **Customer Table** expanded with fields: `phone_number`, `address`, `date_of_birth`.
   - Introduced **InsurancePolicy** table to manage policies linked to customers.
   - Established one-to-many relationship between customers and insurance policies.

3. **Error Handling and Validation**:

   - Implemented try-catch blocks to handle SQL exceptions and provide user-friendly messages.
   - Input validation for fields like email formats, phone numbers, and mandatory fields.
   - Prevents insertion of duplicate customer IDs.

4. **Logging Integration**:

   - Used `java.util.logging` to log significant events.
   - Logs database connections, CRUD operations, and exceptions.
   - Helps in debugging and monitoring application behavior.

5. **Project Structure Refactoring**:

   - Organized code into layers:
     - **Repository Layer**: Handles direct database interactions (`CustomerRepository`, `InsurancePolicyRepository`).
     - **Service Layer**: Contains business logic (`CustomerService`, `InsurancePolicyService`).
     - **UI Layer**: Manages user interactions (`ConsoleUI`, `InsuranceAgencyGUI`).

6. **User Interface**:

   - **GUI with Swing**:
     - Provides a graphical interface for all CRUD operations.
     - Features forms and dialogs for adding, viewing, updating, and deleting records.
   - **Console-Based UI**:
     - Offers a text-based menu for interacting with the application.

7. **Testing and Documentation**:

   - **Unit Testing**:
     - JUnit tests implemented for service methods.
     - Located in the `src/tests` directory.
   - **JavaDocs**:
     - Documented classes and methods for better readability.

## Running Tests

To run the JUnit tests:

```bash
java -cp "out:lib/*" org.junit.runner.JUnitCore com.insuranceagency.service.CustomerServiceTest

---

### **Section 9: Future Enhancements**

```markdown
## Future Enhancements

1. **Claims and Payments**: Add modules to handle insurance claims and payment processing.

2. **Advanced UI**: Enhance the GUI with better design and additional features like search and filters.

3. **Authentication**: Implement user authentication and authorization for different roles.

4. **Deployment**: Package the application as a standalone executable or installer.
````
````
