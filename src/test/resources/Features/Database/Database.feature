Feature: MySQL Database Utilities

  @DatabaseCreation
  Scenario: User creates and connects to the MySQL database and verifies the database name
    Given User creates a database "testhouse"
    And User connects to the database
    Given User connects to the created database
      | Database  | Username | Password |
      | testhouse | root     | admin123 |
    Then verify the database name created

  @TableQuery
  Scenario: User creates a table and inserts data
    Given User connects to the created database
      | Database  | Username | Password |
      | testhouse | root     | admin123 |
    Given User creates a table "customers" with columns "customer_id INT, name VARCHAR(255), email VARCHAR(255)"
    And User inserts a record into the "customers" table with values "1, 'John Doe', 'johndoe@example.com'"
    And User inserts multiple records into the "customers" table with values
      | 2 | Jo Doe     | jodoe@example.com     |
      | 3 | Jane Smith | janesmith@example.com |
      | 4 | Alice Lee  | alicelee@example.com  |
    Then the "customers" table should display records with "customer_id = 4"

  @ValidateQuery
  Scenario: User validates data in the table
    Given User connects to the created database
      | Database  | Username | Password |
      | testhouse | root     | admin123 |
    When User retrieves the total count of records from the "customers" table
    Then the total count of records should be "4"
    When User retrieves the "name" and "email" columns from the "customers" table
    Then the retrieved data should match the following details
      | name       | email                 |
      | John Doe   | johndoe@example.com   |
      | Jo Doe     | jodoe@example.com     |
      | Jane Smith | janesmith@example.com |
      | Alice Lee  | alicelee@example.com  |

    When User searches for a record in the "customers" table where "email = 'johndoe@example.com'"
    Then the record should exist

  @RetrieveData
  Scenario: Retrieve and Validate Data with Complex Conditions
    Given User connects to the created database
      | Database  | Username | Password |
      | testhouse | root     | admin123 |
    And User creates the orders table and adds amounts
    When User retrieves the sum of "total_amount" from the "orders" table
    Then the sum of "total_amount" should be "1000"
    When User retrieves the "name" and "email" columns from the "customers" table where "customer_id > 0 AND email LIKE '%@example.com'"
    Then the retrieved data should match the following details
      | name       | email                 |
      | John Doe   | johndoe@example.com   |
      | Jo Doe     | jodoe@example.com     |
      | Jane Smith | janesmith@example.com |
      | Alice Lee  | alicelee@example.com  |