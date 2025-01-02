@EndToEndFlow_Sales
Feature: Salesforce sales module functionality

  @LoginScenario
  Scenario: 001_Login to salesforce using credentials
    Then User opens Url "https://login.salesforce.com/?locale=in"
    Given I am on the Salesforce login page
    When I enter my username "jone@testhouse.com" and password "Haniya@1"
    And I click on the login button
    Then I should be logged in to Salesforce successfully

  @leadCreation
  Scenario: 002_Creation of a new lead
    Given I am on the salesforce home page
    When I navigate to the app launcher and go to sales page
    Then I go to the leads page
    And I click on new button
    Then I will create a new lead and verify it

  @taskFromLead
  Scenario Outline: 003_Creation of a new Task from Lead
    Given I am on the created Lead's detail page
    When I click on the New Task button
    Then the Task creation form should be displayed with Lead data pre-filled
    And I fill in the task details with "<Subject>" and "<Due Date>" and save it
    Then the task should be created successfully

    Examples:
      | Subject         | Due Date      |
      | Call            | 27-April-2025 |

  @leadProgression
  Scenario Outline: 004_Lead Progression
    Given I am on the created Lead's detail page
    When I click on the Details and change the lead status to "<Change lead Status>"
    Then the working-contacted lead status window should be opened
    And I update the details in that page and click save
    Then I click on the converted and select converted status button

    Examples:
      | Change lead Status  |
      | Working - Contacted |

  @leadConversion
  Scenario: 005_Lead Conversion
    Given I am on the lead's detail conversion screen
    When I click on the Convert button
    Then the lead should be converted and accounts, contacts, and opportunities should be automatically created
    And close the lead converted window

  @ContactGlobalSearch
  Scenario Outline: 006_Perform global search for finding out the contact
    Given I am on the leads page of sales
    When I search the contact name "<contactName>" on the search bar
    Then verify user present on the search results and click "<contactName>" and Role "<Role>"
    Then I should verify the contact name

    Examples:
      | contactName   | Role      |
      | John Thomas   | Contact   |

  @globalSearchAccount
  Scenario Outline:  007_Perform global search for finding out the account
    Given I am on the leads page of sales
    When I search the contact name "<accountName>" on the search bar
    Then verify user present on the search results and click "<accountName>" and Role "<Role>"
    Then I should verify the account name

    Examples:
       | accountName           | Role       |
       | GetCloudy Logistics   | Account    |

  @linkParentAccount
  Scenario Outline:  008_Link an existing parent account to the business account
    Given I am on the account page of the created lead
    When I click on the details button
    And I link an existing parent account named "<parentaccount>" to the business account
    Then I should verify the parent-child relation of "<parentaccount>" and "<childaccount>"

    Examples:
      | parentaccount      | childaccount          |
      | GetCloudy Pvt LTD  | GetCloudy Logistics   |

  @globalSearchOpportunity
  Scenario Outline:  009_Perform global search for finding out the opportunity
    Given I am on the leads page of sales
    When I search the contact name "<opportunityName>" on the search bar
    Then verify user present on the search results and click "<opportunityName>" and Role "<Role>"
    Then I should verify the opportunity name

    Examples:
      | opportunityName      | Role        |
      | GetCloudy Logistics- | Opportunity |

  @choosePriceBook
  Scenario: 010_Choosing a Price Book from Opportunity Products
    Given I am on the specific opportunity page
    When I click on Products and select the Choose Price Book option
    Then the Standard price book should be chosen successfully and saved

  @addProducts
  Scenario: 011_To add products to the selected price book from Opportunity Products
    Given I am on the specific opportunity page
    When I click on Products and select the Add Products option
    And I select desired products and click next
      |GenWatt Diesel 1000kW|
      |GenWatt Diesel 10kW|
    When I enter the quantity of selected products
    Then the products are added with required quantity from opportunity successfully

  @priceCompare
  Scenario: 012_Calculation and comparison of price from products screen and amount field
    Given I am on the opportunity page of the created lead
    When I go to the details field in the value proposition stage
    Then I should get the total amount from the opportunity screen amount field
    Then I should verify that the amount field is the sum of all added products from the products screen
    Then I should compare both the price from the products screen and the amount field

  @quoteCreation
  Scenario: 013_Creation of new quote
    Given I am on the opportunity page of the created lead
    When I click on Quotes and then click New Quote
    Then I should verify that the selected opportunity name is displayed on the New Quote screen
    Then I should verify that the status is set to Draft
    And I create a new quote from the opportunity screen
    Then I create multiple quotes from the opportunity screen

  @verifyQuote
  Scenario: 014_To sync and verify a quote
    Given I am on the opportunity page of the created quotes
    When I select a particular quote and click on the Sync button
    And I confirm the sync action in the confirmation dialog
    Then the syncing checkbox should be ticked under quote

  @syncQuote
  Scenario: 015_Attempt to sync another quote and capture sync error
    Given I am on the opportunity page of the created quotes
    When I click on another quote and click on the Sync button
    Then a sync error message should be displayed and verified

  @createContract
  Scenario Outline: 016_creation of a new contract
    Given I am on the quote page
    When I go to the contract page and create a contract with "<accountname>", "<date>" and "<contractTerm>"
    Then I should verify the newly created contract

    Examples:
      | accountname          | date           | contractTerm |
      | GetCloudy Logistics  | 10-March-2025  | 1            |

  @createOrder
  Scenario Outline: 017_order creation
    Given I am on the created contract page
    When I create a new order with "<ordername>", "<date>"
    Then The created order should be verified with the contract number
    And I will go to the particular opportunity "<opportunityName>" with "<Role>" in the opportunity page
    Then I should link order field with the number

    Examples:
      | ordername             | date           | opportunityName      | Role        |
      | GetCloudy Logistics   | 30-March-2025  | GetCloudy Logistics- | Opportunity |

  @closeOpportunity
  Scenario Outline: 018_Setting Closed to opportunity stages
    Given I am on the opportunity page
    When I click and select closed stage
    And I change the stage to "<Stage>"
    Then the stages should be closed successfully

    Examples:
      | Stage          |
      | Closed Won     |

  @listview
  Scenario Outline: 019_ListView
    Given I am on the opportunity page
    Then I will go to the opportunity page
    And I will create a new listview with "<listviewName>" and new filter
    Then Verify that the created listview contains the opportunity "<opportunityname>"

    Examples:
      | listviewName                    | opportunityname      |
      | Opportunities-Value Proposition | GetCloudy Logistics- |


