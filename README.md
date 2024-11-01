# Insurance Agency CRUD Application

This project is a Java-based CRUD application designed to manage customer records and insurance policies for an insurance agency. It uses MySQL for the database and JDBC for database connectivity.

## Features
- **Create**: Add new customers and insurance policies to the database.
- **Read**: Retrieve and view customer records and their associated policies.
- **Update**: Modify customer details and update policy information.
- **Delete**: Remove customer records and associated policies.

## Database Design
- **Customer Table**:
  - Fields: `id`, `name`, `email`, `phone_number`, `address`, `date_of_birth`
- **InsurancePolicy Table**:
  - Fields: `policy_id`, `customer_id`, `policy_number`, `policy_type`, `coverage_amount`, `start_date`, `end_date`
  - Relationship: `customer_id` in `InsurancePolicy` links to `id` in `Customer`, representing a one-to-many relationship between customers and policies.

## Technologies Used
- **Java**
- **MySQL** and **MySQL Connector/J**
- **JDBC** for database connectivity

## Setup Instructions
1. **Install MySQL**: Ensure MySQL is installed and running.
2. **Create Database and Tables**:
   - Create a database called `insurance_agency_db`.
   - Create the `customers` and `insurance_policies` tables using the following structure:

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
3. **Configure Database Connection**:
   - Update `db.properties` with your database URL, username, and password.
4. **Run the Application**:
   - Compile the application:
     ```bash
     javac -d src src/main/java/com/insuranceagency/*.java
     ```
   - Run the application:
     ```bash
     java -cp "src:lib/mysql-connector-j-9.1.0.jar" main.java.com.insuranceagency.Main
     ```

## Future Improvements
- Additional validation and error handling
- Enhanced user interface for ease of use
- Improved security measures for database access
- Additional modules to manage claims, payments, etc.

---

### Commands to Commit the README Update

1. **Save the Updated README.md**:
   - Open the README.md file in your VS Code editor, paste in the updated content above, and save the file.

2. **Add the Updated README.md to Staging**:
   ```bash
   git add README.md