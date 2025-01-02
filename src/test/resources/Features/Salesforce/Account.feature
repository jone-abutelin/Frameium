Feature: New account creation
  Scenario Outline: Creation of a new account
    Then User opens Url "https://login.salesforce.com/?locale=in"
    Given I login with username "<username>" and password "<password>" of salesforce
    When I navigate to the app launcher and go to sales page
    Then I go to the accounts page and create new account with name "<accountname>"
    Then The created account should be shown

    Examples:
      | username             | password     | accountname          |
      | jone@testhouse.com   |  Haniya@1    | GetCloudy Pvt LTD    |