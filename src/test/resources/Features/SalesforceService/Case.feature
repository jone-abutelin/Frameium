Feature: Case functionality
  @caseCreate
  Scenario Outline: Creation of a new case
    Then User opens Url "https://login.salesforce.com/?locale=in"
    Given I login with username "<username>" and password "<password>" of salesforce
    When I navigate to the app launcher and go to service console page
    Then I go to the case page
    And I click on new case button
    Then I will create a new case with "<contactname>" and "<accountname>" and verify it

    Examples:
      | username             | password  | contactname | accountname           |
      | gt@testhouse.sandbox | Ethan@123 | Jane Doe    | Global Tech Solutions |

  @EditCase
  Scenario Outline: Edit case owner
    When I click on Edit Case Owner
    And I remove Owner
    And I click Users
    And I select Queues from dropdown
    When I search for Owner "<ownername>"
    And I click on Owner from dropdown and save it
    Then I verify Case Owner

    Examples:
      | ownername      |
      | platinum       |