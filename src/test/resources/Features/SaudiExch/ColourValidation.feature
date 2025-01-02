@PageColourValidations
Feature: Verify Colour of Elements in Home Page

  @P3
  @VeriyingTextsColors1
  Scenario: Verify that texts - Market Status,Date,Search appear in white.
    Then User opens the Url "http://www.saudiexchange.sa/"
    Then Verify date appears in white
    Then Verify Market Status appears in white
    And Verify Search symbol appears in white

  @P3
  @VeriyingTextsColors2
  Scenario: Verify data appears in green if price up and red if price is down
    Then User opens the Url "http://www.saudiexchange.sa/"
    Then Verify that the color appears green when the market is up and red when the market is down for TASI
    And Verify that the color appears green when the market is up and red when the market is down for MT30
    And Verify that the color appears green when the market is up and red when the market is down for Nomuc