@LoginSalesforce
Feature: Salesforce Login Functionality

  Scenario Outline: Login to salesforce using credentials
    Then User opens Url "https://testhouse2-dev-ed.develop.my.salesforce.com"
    Given I am on the Salesforce login page
    When I enter my username "<username>" and password "<password>"
    And I click on the login button
    Then I should be logged in to Salesforce successfully

    Examples:
      | username             | password     |
      | gt@testhouse.sandbox | Ethan@123    |