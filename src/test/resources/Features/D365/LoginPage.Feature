Feature: Account Page Functionality
 
  Scenario: Valid login and logout
  	Given Launch chrome browser
  	Given I am on the landing page of D365
    Then I should be able to create an account
		Then I should be able to create a competitor
		Then I should be able to create a lead
		Then I should be able to create a task
		Then I should be able to verify the error message
		Then I should be able to delete all leads
		Then I should be able to logout