Feature: Contract and order functionalities
  Scenario Outline: creation of a new contract
    Then User opens Url "https://login.salesforce.com/?locale=in"
    Given I login with username "<username>" and password "<password>" of salesforce
    And I am on the salesforce leads page
    When I go to the contract page and create a contract with "<accountname>", "<date>" and "<contractTerm>"
    Then I should verify the newly created contract

    Examples:
      | username             | password     | accountname          | date           | contractTerm |
      | jone@testhouse.com   | Haniya@1     | GetCloudy Logistics  | 10-March-2025  | 1            |

  Scenario Outline: order creation
    Given I am on the created contract page
    When I create a new order with "<ordername>", "<date>"
    Then The created order should be verified with the contract number
    And I will go to the particular opportunity "<opportunityName>" with "<Role>" in the opportunity page
    Then I should link order field with the number

    Examples:
      | ordername             | date           | opportunityName      | Role        |
      | GetCloudy Logistics   | 30-March-2025  | GetCloudy Logistics- | Opportunity |

  Scenario Outline: Setting Closed to opportunity stages
    Given I am on the opportunity page
    When I click and select closed stage
    And I change the stage to "<Stage>"
    Then the stages should be closed successfully

    Examples:
      | Stage          |
      | Closed Won     |
















#  Scenario Outline:  Perform global search for finding out the opportunity
#    Given Launch chrome browser
#    Then User opens Url "https://login.salesforce.com/?locale=in"
#    Given I login with username "<username>" and password "<password>" of salesforce
#    And I am on the salesforce leads page
#    And I will go to the particular opportunity "<opportunityName>" with "<Role>" in the opportunity page
#
#
#    Examples:
#      | username             | password | opportunityName      | Role        |
#      | saniga@testhouse.com | 2468@san | GetCloudy Logistics- | Opportunity |
#
