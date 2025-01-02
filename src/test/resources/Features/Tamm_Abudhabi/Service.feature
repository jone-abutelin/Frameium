@ServicePage
Feature: Service Page Functionality
  Background:
    Then User opens Url "https://www.tamm.abudhabi/"
    When user click Services button


  @Cal
  Scenario Outline: Calculate Previous Service Amount
    When user click Work & Education button
    When user click Type button from Work & Education
    When user selects Emirati from the checkbox
    When user clicks the Calculate Previous Service Amount button
    Then the user should be successfully navigated to the <expectedText> page
    When user click popup button
    When user click dropdown button
    When user Selects  Add Period one by one from the options
    When user selects the date "<FromDate>" in the form
    When user selects the  To date "<ToDate>" in the form
    When user click Add button
    When user click Amount button
    When user enters an "<Amount>"
    When user click Calculate button
    When User rates their effort with smiley number "<effortRating>"
    When User rates their satisfaction with smiley number "<satisfactionRating>"
    When user click next button
    When User provides feedback reason "<reason>"
    Then the user should add an additional comment about their experience "<comment>"
    When user click confirm button
    Then the user should successfully see the title 'Thank you for your feedback!'
    When user click My Tamm button
    Then the user should be successfully navigated to the home page and the URL should be "https://www.tamm.abudhabi/login"



    Examples:
      | expectedText                        | FromDate   | ToDate     | Amount | effortRating | satisfactionRating | reason      | comment                                            |
      | "Calculate Previous Service Amount" | 12/30/2007 | 11/24/2012 | 2000   | 4            | 4                  | Ease of Use | The website is very intuitive and easy to navigate |

  @Pension
  Scenario Outline: pension calculator
    When user click Work & Education button
    Then click the pension calculator Option
    When user click popup button
    When user is on the pension calculator page"<expectedtitle>"
    Then user select the "<dateofbirth>"
    Then User Select the gender
    Then user selects the service period
    Then user enter the  last pension salary "<salary>"
    Then user enter the  Average pension salary "<avg>"
    When user click Calculate button
    When User rates their effort with smiley number "<effortRating>"
    When User rates their satisfaction with smiley number "<satisfactionRating>"
    When user click next button
    When User provides feedback reason "<reason>"
    Then the user should add an additional comment about their experience "<comment>"
    When user click confirm button
    Then the user should successfully see the title 'Thank you for your feedback!'
    When user click My Tamm button
    Then the user should be successfully navigated to the home page and the URL should be "https://www.tamm.abudhabi/login"

    Examples:
      | dateofbirth |  effortRating | satisfactionRating | expectedtitle      | reason      | comment                                            |salary  |avg |
      | 09/29/1965  |  3            | 4                  | Pension Calculator |Ease of Use | The website is very intuitive and easy to navigate |2500     |1200|

  @licence
  Scenario: licence Comparison

    Then choose the business option
    Then select the start business option
    Then select start a business option
    When select start new journey with existing licence
    When select commercial option
    And click the proceed option
    Then click on comparison tool
    Then choose licence option
    Then select the four types of licences
    And click the compare option