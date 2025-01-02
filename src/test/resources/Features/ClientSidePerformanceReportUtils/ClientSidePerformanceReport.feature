@tamm
Feature: Tamm Abudhabi Website

  @lightHouse
  Scenario: Selection of service from checklist
    Then User opens Url "https://www.tamm.abudhabi/"
    When User Click the Government Entities
    And User Click AbuDhabi Pension FundButton
    And User Click Type button
    And User Selects Visitor from the checkbox
    And User Click Pension Increase Calculatorbutton
    Then User Should be successfully navigated to the page 'Pension Increase Calculator'