@LoginSalesforce
Feature: Salesforce Login Functionality

 Scenario Outline: Login to salesforce using credentials
    Then User opens Url "https://login.salesforce.com/?locale=in"
    Given I am on the Salesforce login page
    When I enter my username "<username>" and password "<password>"
    And I click on the login button
    Then I should be logged in to Salesforce successfully
    
    Examples:
       | username           | password     |
       | jone@testhouse.com | Haniya@1  |