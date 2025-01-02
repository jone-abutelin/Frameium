@OpportunityGlobalSearch
Feature: Opportunity Functionality

  Scenario Outline:  Perform global search for finding out the opportunity
    Then User opens Url "https://login.salesforce.com/?locale=in"
    Given I login with username "<username>" and password "<password>" of salesforce
    And I am on the sales leads page
    When I search the contact name "<opportunityName>" on the search bar
    Then verify user present on the search results and click "<opportunityName>" and Role "<Role>"
    Then I should verify the opportunity name

    Examples:
      | username           | password | opportunityName      | Role        |
      | jone@testhouse.com | Haniya@1 | GetCloudy Logistics- | Opportunity |

  Scenario: Choosing a Price Book from Opportunity Products
    Given I am on the specific opportunity page
    When I click on Products and select the Choose Price Book option
    Then the Standard price book should be chosen successfully and saved

  Scenario: To add products to the selected price book from Opportunity Products
    Given I am on the specific opportunity page
    When I click on Products and select the Add Products option
    And I select desired products and click next
    |GenWatt Diesel 1000kW|
    |GenWatt Diesel 10kW|
    When I enter the quantity of selected products
    Then the products are added with required quantity from opportunity successfully

  Scenario:Calculation and comparison of price from products screen and amount field
    Given I am on the opportunity page of the created lead
    When I go to the details field in the value proposition stage
    Then I should get the total amount from the opportunity screen amount field
    Then I should verify that the amount field is the sum of all added products from the products screen
    Then I should compare both the price from the products screen and the amount field