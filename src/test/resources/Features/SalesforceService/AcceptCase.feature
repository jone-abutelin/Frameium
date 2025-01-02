Feature: Skill based routing

  Scenario Outline: Automatic routing of a case based on agent's skills
    Given User opens Url "https://login.salesforce.com/?locale=in"
    When I login with username "<username>" and password "<password>" of salesforce
    And I navigate to the app launcher and go to service console page
    Then I will go to the case page and create a new case  with "<contactname>", "<accountname>", "<type>" and "<reason>"
    And I will change the owner name to "<ownername>"
    And I will go to the omnichannel to made the status available
    Then I change the owner and select the queue "<queuename>" that is configured for routing based on skills


    Examples:
      | username             | password  | contactname | accountname           | type       | reason    |ownername   | queuename   |
      | gt@testhouse.sandbox | Ethan@123 | Jane Doe    | Global Tech Solutions | Electronic | Breakdown |Thomas Arun | AutoRouting |

  Scenario: Accept routed case from omnichannel widget
    Given I am on created case page
    Then I navigate to omnichannel widget
    And I Verify that omnichannel widget is updated with the routed case
    Then I Click on accept the case