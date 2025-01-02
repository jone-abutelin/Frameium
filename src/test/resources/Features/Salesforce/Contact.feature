Feature: Global search
  Background:
    Then User opens Url "https://login.salesforce.com/?locale=in"

  @ContactGlobalSearch
  Scenario Outline: Perform global search for finding out the contact
    Given I login with username "<username>" and password "<password>" of salesforce
    And I am on the sales leads page
    When I search the contact name "<contactName>" on the search bar
    Then verify user present on the search results and click "<contactName>" and Role "<Role>"
    Then I should verify the contact name

    Examples:
      | username             | password | contactName   | Role      |
      | jone@testhouse.com   | Haniya@1 | John Thomas   | Contact   |