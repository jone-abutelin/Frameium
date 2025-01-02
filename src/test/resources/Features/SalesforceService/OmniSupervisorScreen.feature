Feature: Omnichannel Routing
  @OmniChannel
  Scenario Outline: Verify Case Details on Omni Supervisor screen
    Then User opens Url "https://testhouse2-dev-ed.develop.my.salesforce.com"
    Given I login with username "<username>" and password "<password>" of salesforce
    When I navigate to the app launcher and go to service console page
    When I set Omni-Channel to available state
    And I navigate to Omni Supervisor page
    And I navigate to Assigned Work
    Then I verify the AutoRouting Queue
    When I click on Queue
    Then I verify Case Details

    Examples:
      | username             | password  |
      | gt@testhouse.sandbox | Ethan@123 |




