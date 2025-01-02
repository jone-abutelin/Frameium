Feature: ListView feature
  Scenario Outline: ListView
    Then User opens Url "https://login.salesforce.com/?locale=in"
    Given I login with username "<username>" and password "<password>" of salesforce
    And I am on the salesforce leads page
    Then I will go to the opportunity page
    And I will create a new listview with "<listviewName>" and new filter
    Then Verify that the created listview contains the opportunity "<opportunityname>"

    Examples:
      | username               | password     | listviewName                    | opportunityname      |
      | jone@testhouse.com     | Haniya@1     | Opportunities-Value Proposition | GetCloudy Logistics- |