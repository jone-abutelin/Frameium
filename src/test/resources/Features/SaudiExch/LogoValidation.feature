@VisualLogo
Feature: LogoImageValidation
  I am Comparing the logo on Home Page

  @prodLogo
  Scenario Outline: Downloading logo from Production and saving
    Then User opens Url "https://www.saudiexchange.sa/"
    When I am on the home page of SaudiExchange in production from Logo
    Then I go to logo and copy logo by giving name as <name> and I save the logo from production
    Examples:
      |			name  		    |
      | "saudiExLogoOnToppro"	|
      | "saudiTadawulLogopro"	|

  @uatLogo
  Scenario Outline: Downloading logo from uat and Comparing_Logo of both production and
    Then User opens Url "https://www.saudiexchange.sa/"
    When I am on the home page of SaudiExchange in uat from Logo
    Then I go to logo and copy logo by giving name as <ExpectedLogo> and I save the logo from uat
    Then I Compare the <ActualLogo> and <ExpectedLogo> both the Logo must be same
    Examples:
      |         ActualLogo    |      ExpectedLogo     |
      | "saudiExLogoOnToppro" | "saudiExLogoOnTopuat" |
      | "saudiTadawulLogopro" | "eddaLogouat"         |






