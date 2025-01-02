@sortingValidation
Feature: Verify Table Sort
  Verify table sort functionality for the table in the Market Performance Page

  Scenario: Verify table sort for table under Market Performance Page
    When User opens the Url "https://www.saudiexchange.sa/wps/portal/saudiexchange/home/"
    When User is on the Market Performance page
    Then I store the data of Highest column in a list and sort it by java function
    Then I sort the data of highest column from website and store it in a list
    Then I compare both the list it must be matched



