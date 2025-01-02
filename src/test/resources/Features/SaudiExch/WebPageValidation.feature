@VisualWebPage
Feature: WebPageValidation
  I am Comparing the Webpages

  @prodPage
  Scenario Outline: Downloading webPage from Production and saving
    Then User opens Url "https://www.saudiexchange.sa/"
    When I am on the home page of SaudiExchange in production
    Then I go to webPage and I save the webPage by giving name as the <name> from production
    Examples:
      | name          |
      | "webPagepro"  |
      | "webPagepro1" |

  @uatPage
  Scenario Outline: Downloading image from uat and Comparing_Image of both production and Uat
    Then User opens Url "https://www.saudiexchange.sa/"
    When I am on the home page of SaudiExchange in UAT
    Then I go to webPage and I save the webPage by giving name as the <ExpectedWebPage> from uat
    Then I Compare the <ActualWebPage> and <ExpectedWebPage> both WebPages It must be same and if it is different then it should create a differencewebPage by name <DifferencewebPage>
    Examples:
      | ActualWebPage | ExpectedWebPage | DifferencewebPage |
      | "webPagepro"  | "webPageuat"    | "webPageDiff"     |
      | "webPagepro1" | "webPageuat1"   | "webPageDiff1"    |