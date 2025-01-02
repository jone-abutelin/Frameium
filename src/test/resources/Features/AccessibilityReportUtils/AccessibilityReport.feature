 @tamm_abudhabi
Feature: Government Entities Page Accessibility
  @Accessibility
  Scenario: Navigate to Government Entities
    Then User opens Url "https://www.tamm.abudhabi/"
    When User click Government Entities button
    And User click Department of Health button
    And User click  Health button
    And User click  initial Approval button
    Then User should be successfully navigated and see the heading 'Initial Approval for Health Licence'
