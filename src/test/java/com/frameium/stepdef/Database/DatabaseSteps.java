package com.frameium.stepdef.Database;

import com.frameium.stepdef.TestSetUp;
import com.frameium.utilities.DatabaseUtils.MySQLDatabaseUtil;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;


public class DatabaseSteps {
    private Connection connection;

    private WebDriver driver;
    public MySQLDatabaseUtil dbUtil;
    TestSetUp setUp;
    private String currentDbName;
    private Map<String, Object> testContext = new HashMap<>(); // Local context for data sharing
    private List<Map<String, String>> retrievedData = new ArrayList<>(); // To store retrieved data
    private boolean recordExists; // Flag to indicate if the record was found
    private int sumResult; // Stores the sum of a column

    public DatabaseSteps(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        dbUtil = new MySQLDatabaseUtil(setUp.baseTest.driver);

        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }

    @Given("User creates a database {string}")
    public void user_creates_a_database(String dbName) {
        try (Connection conn = MySQLDatabaseUtil.getConnectionWithoutDB()) {
            String createDbQuery = "CREATE DATABASE IF NOT EXISTS " + dbName;
            conn.createStatement().executeUpdate(createDbQuery);
            System.out.println("Database " + dbName + " created successfully.");
            // Now, check if the database is available
            if (MySQLDatabaseUtil.isDatabaseAvailable(dbName)) {
                System.out.println("Database " + dbName + " is available for connection.");
            } else {
                System.err.println("Database " + dbName + " was not created successfully.");
                fail("Database creation failed or database is not available.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to create database: " + e.getMessage());
            e.printStackTrace();
            fail("Database creation failed: " + e.getMessage());
        }
    }

    @And("User connects to the database")
    public void user_connects_to_the_database() throws SQLException {
        try {
            connection = MySQLDatabaseUtil.getConnection();
            System.out.println("Database connection established successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            e.printStackTrace();
            fail("Database connection failed: " + e.getMessage());
        }
    }

    @Given("User connects to the created database")
    public void user_connects_to_the_created_database(io.cucumber.datatable.DataTable dataTable) {
        // Convert the DataTable to a List of Maps for multiple rows
        List<Map<String, String>> dbDetailsList = dataTable.asMaps(String.class, String.class);

        // Assuming only one row in the DataTable, retrieve the first entry
        Map<String, String> dbDetails = dbDetailsList.get(0);

        // Extract values from the first row
        String database = dbDetails.get("Database");
        this.currentDbName = database;
        String username = dbDetails.get("Username");
        String password = dbDetails.get("Password");

        // Construct the database URL dynamically
        String dbUrl = "jdbc:mysql://localhost:3306/" + database;

        // Try connecting to the database
        try {
            connection = MySQLDatabaseUtil.getConnectionForDB(dbUrl, username, password);
            System.out.println("Connected to the database " + database + " successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database " + database + ": " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Database connection failed: " + e.getMessage());
        }
    }

    @Then("verify the database name created")
    public void verify_the_database_name_created() {
        try {
            if (connection != null) {
                String actualDbName = connection.getCatalog();
                if (currentDbName.equals(actualDbName)) {
                    System.out.println("Database verification successful. Database name is: " + actualDbName);
                } else {
                    System.err.println("Database verification failed. Expected: " + currentDbName + ", but found: " + actualDbName);
                    fail("Database name mismatch.");
                }
            } else {
                fail("No active database connection to verify.");
            }
        } catch (SQLException e) {
            System.err.println("Failed to verify database name: " + e.getMessage());
            e.printStackTrace();
            fail("Database name verification failed: " + e.getMessage());
        }
    }


    @Given("User creates a table {string} with columns {string}")
    public void user_creates_a_table_with_columns(String tableName, String columns) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + columns + ")";
        try (
                PreparedStatement stmt = connection.prepareStatement(createTableQuery)) {
            stmt.execute();
            System.out.println("Table " + tableName + " created successfully with columns: " + columns);
        } catch (SQLException e) {
            System.err.println("Failed to create table " + tableName + ": " + e.getMessage());
            throw new RuntimeException("Table creation failed: " + e.getMessage());
        }
    }

    @And("User inserts a record into the {string} table with values {string}")
    public void user_inserts_a_record_into_the_table_with_values(String tableName, String values) {
        String insertQuery = "INSERT INTO " + tableName + " VALUES (" + values + ")";
        try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
            int rowsInserted = stmt.executeUpdate();
            System.out.println("Inserted " + rowsInserted + " record(s) into the " + tableName + " table.");
        } catch (SQLException e) {
            System.err.println("Failed to insert record into table " + tableName + ": " + e.getMessage());
            throw new RuntimeException("Record insertion failed: " + e.getMessage());
        }
    }

    @And("User inserts multiple records into the {string} table with values")
    public void user_inserts_multiple_records_into_table(String tableName, io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        String query = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (List<String> row : rows) {
                preparedStatement.setInt(1, Integer.parseInt(row.get(0))); // Assuming first column is INT
                preparedStatement.setString(2, row.get(1));               // Second column is String
                preparedStatement.setString(3, row.get(2));               // Third column is String
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            System.out.println("Records inserted successfully into the " + tableName + " table.");
        } catch (SQLException e) {
            System.err.println("Failed to insert records into the table: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Record insertion failed: " + e.getMessage());
        }
    }

    @Then("the {string} table should display records with {string}")
    public void the_table_should_display_record_with(String tableName, String condition) {
        String selectQuery = "SELECT * FROM " + tableName + " WHERE " + condition;
        try (PreparedStatement stmt = connection.prepareStatement(selectQuery);
             ResultSet resultSet = stmt.executeQuery()) {
            boolean recordFound = false;

            // Iterate through the result set and print the details of each matching record
            while (resultSet.next()) {
                // Assuming 'customer_id', 'name', 'email' are the columns you want to retrieve
                int customerId = resultSet.getInt("customer_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                // Print all columns for the matching record
                System.out.println(customerId + "\t" + name + "\t" + email);

                // Set recordFound to true if matching records exist
                recordFound = true;
            }

            if (!recordFound) {
                throw new AssertionError("No matching records found in the " + tableName + " table with condition: " + condition);
            }

        } catch (SQLException e) {
            System.err.println("Failed to verify records in table " + tableName + ": " + e.getMessage());
            throw new RuntimeException("Record retrieval failed: " + e.getMessage());
        }
    }

    @When("User retrieves the total count of records from the {string} table")
    public void user_retrieves_total_count_of_records(String tableName) {
        try {
            String query = "SELECT COUNT(*) AS total_count FROM " + tableName;
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            if (resultSet.next()) {
                int totalCount = resultSet.getInt("total_count");
                // Store total count in the local context map
                testContext.put("totalCount", totalCount);
                System.out.println("Total count of records in table '" + tableName + "': " + totalCount);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving total count: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve total count: " + e.getMessage());
        }
    }

    @Then("the total count of records should be {string}")
    public void validate_total_count_of_records(String expectedCount) {
        int actualCount = (int) testContext.get("totalCount");
        int expected = Integer.parseInt(expectedCount);

        if (actualCount != expected) {
            throw new AssertionError("Total count mismatch. Expected: " + expected + ", Actual: " + actualCount);
        }
        System.out.println("Total count validation successful. Count: " + actualCount);
    }

    @When("User retrieves the {string} and {string} columns from the {string} table")
    public void user_retrieves_columns_from_table(String column1, String column2, String tableName) {
        try {
            String query = String.format("SELECT %s, %s FROM %s", column1, column2, tableName);
            ResultSet resultSet = connection.createStatement().executeQuery(query);

            // Clear previous data
            retrievedData.clear();

            while (resultSet.next()) {
                Map<String, String> row = new HashMap<>();
                row.put(column1, resultSet.getString(column1));
                row.put(column2, resultSet.getString(column2));
                retrievedData.add(row);
            }

            System.out.println("Retrieved data from table '" + tableName + "': " + retrievedData);
        } catch (SQLException e) {
            System.err.println("Error retrieving columns: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve data: " + e.getMessage());
        }
    }

    @Then("the retrieved data should match the following details")
    public void validate_retrieved_data(io.cucumber.datatable.DataTable expectedData) {
        List<Map<String, String>> expectedRows = expectedData.asMaps(String.class, String.class);

        if (!retrievedData.equals(expectedRows)) {
            throw new AssertionError("Data mismatch. Expected: " + expectedRows + ", Actual: " + retrievedData);
        }

        System.out.println("Retrieved data matches expected details.");
    }

    @When("User searches for a record in the {string} table where {string}")
    public void user_searches_for_a_record_in_table(String tableName, String condition) {
        try {
            String query = "SELECT 1 FROM " + tableName + " WHERE " + condition + " LIMIT 1";
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            recordExists = resultSet.next(); // Set to true if a record is found
            System.out.println("Record exists in table '" + tableName + "' with condition '" + condition + "': " + recordExists);
        } catch (SQLException e) {
            System.err.println("Error searching for record: " + e.getMessage());
            throw new RuntimeException("Failed to search for record: " + e.getMessage());
        }
    }

    @Then("the record should exist")
    public void validate_record_existence() {
        if (!recordExists) {
            throw new AssertionError("Record not found in the table with the given condition.");
        }
        System.out.println("Record exists as expected.");
    }

    @Given("User creates the orders table and adds amounts")
    public void user_creates_orders_table_and_adds_amounts() {
        try (Statement stmt = connection.createStatement()) {
            // Create the orders table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS orders (" +
                    "order_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "total_amount DECIMAL(10, 2) NOT NULL)";
            stmt.execute(createTableQuery);

            // Insert records into the orders table
            String insertDataQuery = "INSERT INTO orders (total_amount) VALUES " +
                    "(200.50), (300.00), (250.25), (249.25)"; // Total = 1000
            stmt.executeUpdate(insertDataQuery);

            System.out.println("Orders table created and data inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating or inserting into orders table: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @When("User retrieves the sum of {string} from the {string} table")
    public void user_retrieves_the_sum_of_column(String columnName, String tableName) {
        try {
            String query = "SELECT SUM(" + columnName + ") AS total_sum FROM " + tableName;
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            if (resultSet.next()) {
                sumResult = resultSet.getInt("total_sum");
                System.out.println("Sum of column '" + columnName + "' in table '" + tableName + "': " + sumResult);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving sum: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve sum: " + e.getMessage());
        }
    }

    @Then("the sum of {string} should be {string}")
    public void validate_sum_of_column(String columnName, String expectedSum) {
        int expected = Integer.parseInt(expectedSum);
        if (sumResult != expected) {
            throw new AssertionError("Sum mismatch for column '" + columnName + "'. Expected: " + expected + ", Actual: " + sumResult);
        }
        System.out.println("Sum validation successful. Sum: " + sumResult);
    }

    @When("User retrieves the {string} and {string} columns from the {string} table where {string}")
    public void user_retrieves_columns_with_conditions(String column1, String column2, String tableName, String condition) {
        retrievedData = new ArrayList<>();
        try {
            String query = "SELECT " + column1 + ", " + column2 + " FROM " + tableName + " WHERE " + condition;
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while (resultSet.next()) {
                retrievedData.add(Map.of(
                        column1, resultSet.getString(column1),
                        column2, resultSet.getString(column2)
                ));
            }
            System.out.println("Retrieved data: " + retrievedData);
        } catch (SQLException e) {
            System.err.println("Error retrieving data: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve data: " + e.getMessage());
        }
    }
}


