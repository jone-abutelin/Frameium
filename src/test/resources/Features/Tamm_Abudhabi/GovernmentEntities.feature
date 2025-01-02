@GovernmentEntitiesPage
Feature: Government Entities Page Functionality
  @GovernmentEntities
  Scenario: Navigate to Government Entities Page
    Then User opens Url "https://www.tamm.abudhabi/"
    When user click Government Entities button
    When user click Department of Health button
    When user click  Health button
    When user click  initial Approval button
    Then the user should be successfully navigated and see the heading 'Initial Approval for Health Licence'

    @type
    Scenario: Selection from Checklist
      Then User opens Url "https://www.tamm.abudhabi/"
      When user click Government Entities button
      When user click AbuDhabi Pension Fund button
      When user click Type button
      When the user selects Visitor from the checkbox
      When user click Pension Increase Calculatorbutton
      Then the user should be successfully navigated and see the page 'Pension Increase Calculator'