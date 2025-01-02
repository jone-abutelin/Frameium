@quoteFunctions
Feature: Quote Functionality

  Scenario Outline:  Perform global search for finding out the opportunity
    Then User opens Url "https://login.salesforce.com/?locale=in"
    Given I login with username "jone@testhouse.com" and password "Haniya@1" of salesforce
    And I am on the sales leads page
    When I search the contact name "<opportunityName>" on the search bar
    Then verify user present on the search results and click "<opportunityName>" and Role "<Role>"
    Then I should verify the opportunity name

    Examples:
      | opportunityName      | Role        |
      | GetCloudy Logistics- | Opportunity |

  Scenario: Creation of new quote
    Given I am on the opportunity page of the created lead
    When I click on Quotes and then click New Quote
    Then I should verify that the selected opportunity name is displayed on the New Quote screen
    Then I should verify that the status is set to Draft
    And I create a new quote from the opportunity screen
    Then I create multiple quotes from the opportunity screen

  Scenario: To sync and verify a quote
    Given I am on the opportunity page of the created quotes
    When I select a particular quote and click on the Sync button
    And I confirm the sync action in the confirmation dialog
    Then the syncing checkbox should be ticked under quote

  Scenario: Attempt to sync another quote and capture sync error
    Given I am on the opportunity page of the created quotes
    When I click on another quote and click on the Sync button
    Then a sync error message should be displayed and verified










