Feature: Header layout validation
@VerifyingHeader
Scenario: Validate the header layout on example.com
    Given I open the example website "https://galenframework.com/"
    When I check the header layout
    Then the header layout should match the specifications