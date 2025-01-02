Feature: E2E2 Functionality

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

#  @quoteCreation
#  Scenario: Creation of new quote
#    Given I am on the opportunity page of the created lead
#    When I click on Quotes and then click New Quote
#    Then I should verify that the selected opportunity name is displayed on the New Quote screen
#    Then I should verify that the status is set to Draft
#    And I create a new quote from the opportunity screen
#    Then I create multiple quotes from the opportunity screen
#
#  @verifyQuote
#  Scenario: To sync and verify a quote
#    Given I am on the opportunity page of the created quotes
#    When I select a particular quote and click on the Sync button
#    And I confirm the sync action in the confirmation dialog
#    Then the syncing checkbox should be ticked under quote
#
#  @syncQuote
#  Scenario: Attempt to sync another quote and capture sync error
#    Given I am on the opportunity page of the created quotes
#    When I click on another quote and click on the Sync button
#    Then a sync error message should be displayed and verified
#
#  @createContract
#  Scenario Outline: creation of a new contract
#    Given I am on the quote page
#    When I go to the contract page and create a contract with "<accountname>", "<date>" and "<contractTerm>"
#    Then I should verify the newly created contract
#
#    Examples:
#      | accountname          | date           | contractTerm |
#      | GetCloudy Logistics  | 10-March-2025  | 1            |
#
#  @createOrder
#  Scenario Outline: order creation
#    Given I am on the created contract page
#    When I create a new order with "<ordername>", "<date>"
#    Then The created order should be verified with the contract number
#    And I will go to the particular opportunity "<opportunityName>" with "<Role>" in the opportunity page
#    Then I should link order field with the number
#
#    Examples:
#      | ordername             | date           | opportunityName      | Role        |
#      | GetCloudy Logistics   | 30-March-2025  | GetCloudy Logistics- | Opportunity |
#
#  @closeOpportunity
#  Scenario Outline: Setting Closed to opportunity stages
#    Given I am on the opportunity page
#    When I click and select closed stage
#    And I change the stage to "<Stage>"
#    Then the stages should be closed successfully
#
#    Examples:
#      | Stage          |
#      | Closed Won     |

  @listview
  Scenario Outline: ListView
    Given I am on the opportunity page
    Then I will go to the opportunity page
    And I will create a new listview with "<listviewName>" and new filter
    Then Verify that the created listview contains the opportunity "<opportunityname>"

    Examples:
     | listviewName                    | opportunityname      |
     | Opportunities-Value Proposition14 | GetCloudy Logistics- |


