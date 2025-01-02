Feature: Case functionality
  @caseAutoAssignmentCreate
  Scenario Outline: creation of a new case
    Then User opens Url "https://login.salesforce.com/?locale=in"
    Given I login with username "<username>" and password "<password>" of salesforce
    When I navigate to the app launcher and go to service console page
    Then I go to the case page
    And I click on new case button
    And I will create a new case with "<contactname>" and "<accountname>" with auto-assignment and verify it
    Then I will verify the auto assigned case owner


    Examples:
      | username             | password  | contactname | accountname           |
      | gt@testhouse.sandbox | Ethan@123 | Jane Doe    | Global Tech Solutions |
