Feature: Skill based routing
  @skillBasedRouting
  Scenario Outline: Automatic routing of a case based on agent's skills
    Then User opens Url "https://login.salesforce.com/?locale=in"
    Given I login with username "<username>" and password "<password>" of salesforce
    When I navigate to the app launcher and go to service console page
    Then I will go to the case page and create a new case  with "<contactname>", "<accountname>", "<type>" and "<reason>"
    And I will change the owner name to "<ownername>"
    And I will go to the omnichannel to made the status available
    Then I change the owner and select the queue "<queuename>" that is configured for routing based on skills
    Then I Verify that case is routed to right skilled agent based on type "<type>" and reason "<reason>"


    Examples:
      | username             | password  | contactname | accountname           | type       | reason    |ownername   | queuename   |
      | gt@testhouse.sandbox | Ethan@123 | Jane Doe    | Global Tech Solutions | Electronic | Breakdown |Thomas Arun | AutoRouting |
