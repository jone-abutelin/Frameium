Feature: Login functionality
 
  Scenario: Valid login and logout
  	Then User opens Url "https://www.saucedemo.com/"
    Given I am on the login page
    When I perform a valid login
    Then I should be logged in successfully
    And I perform a logout
    Then I should be logged out
 
  Scenario: Login with special characters
    Given I am on the login page
    When I perform a login with special characters
    Then I should see an error message for special character login
 
  Scenario: Locked user login
    Given I am on the login page
    When I perform a login for a locked-out user
    Then I should see an error message for locked user login
 
  Scenario: Invalid login
    Given I am on the login page
    When I perform an invalid login
    Then I should see an error message for invalid login
    
  Scenario: Empty login
    Given I am on the login page
    When I perform an empty login
    Then I should see an error message for empty login