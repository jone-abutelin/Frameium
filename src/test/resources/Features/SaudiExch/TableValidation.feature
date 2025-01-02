@TableDataValidation
Feature: Verify Table Content

  Scenario: Navigate to Our Market > Nomu-Parallel Market watch and validate Table header
    Then User opens the Url "http://www.saudiexchange.sa/"
    Then Click on Parallel Market Watch menu from Our Market dropdown menu
    Then Select Capital Goods from Industry Group dropdown list
    And Select Price data from Table View dropdown list
    Then Verify the heading of the each column in table:
      | Company         |
      | Price           |
      | Change Value    |
      | Change %        |
      | Open            |
      | High            |
      | Low             |
    Then user Fetch Production Table data and store it in list
    #assume user navigates to UAT site
    Then User opens the Url "http://www.saudiexchange.sa/"
    Then Click on Parallel Market Watch menu from Our Market dropdown menu
    Then Select Capital Goods from Industry Group dropdown list
    And Select Price data from Table View dropdown list
    Then user Fetch UAT Table data and store it in list
    Then user compare prod table data with uat table