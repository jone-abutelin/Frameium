Feature: Logout functionality

  Scenario: User logs out successfully
  	Given Launch chrome browser
  	Then User opens Url "https://www.saucedemo.com/"
    Given I am on the Saucedemo login page
    When I log in with the credentials: "standard_user" and password "secret_sauce"
    And I perform logout action from the user icon
    Then I should see the login button on the main page
    
  Scenario: User accesses the main page after logout
    Given I am on the Saucedemo login page
    When I log in with username "standard_user" and password "secret_sauce"
    And I perform logout from icon
    Then I attempt to access the main page after logout
