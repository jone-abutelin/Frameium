Feature: Global search of account and Parent account linking

  Scenario Outline:  Perform global search for finding out the account
    Then User opens Url "https://login.salesforce.com/?locale=in"
    Given I login with username "<username>" and password "<password>" of salesforce
    And I am on the salesforce leads page
    When I search the contact name "<accountName>" on the search bar
    Then verify user present on the search results and click "<accountName>" and Role "<Role>"
    Then I should verify the account name

    Examples:
      | username             | password | accountName           | Role       |
      | jone@testhouse.com   | Haniya@1 | GetCloudy Logistics   | Account    |


  Scenario Outline:  Link an existing parent account to the business account
    Given I am on the account page of the created lead
    When I click on the details button
    And I link an existing parent account named "<parentaccount>" to the business account
    Then I should verify the parent-child relation of "<parentaccount>" and "<childaccount>"

    Examples:
      | parentaccount      | childaccount          |
      | GetCloudy Pvt LTD  | GetCloudy Logistics   |






