# Insurance Agency CRUD Application

This project is a Java-based CRUD application designed to manage customer records for an insurance agency. It uses MySQL for the database and JDBC for database connectivity.

## Features
- **Create**: Add new customers to the database.
- **Read**: Retrieve and view customer records.
- **Update**: Modify customer details.
- **Delete**: Remove customer records.

## Technologies Used
- **Java**
- **MySQL** and **MySQL Connector/J**
- **JDBC** for database connectivity

## Setup Instructions
1. **Install MySQL**: Ensure MySQL is installed and running.
2. **Create Database and Table**:
   - Create a database called `insurance_agency_db`.
   - Create a table called `customers` with `id`, `name`, and `email` fields.
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