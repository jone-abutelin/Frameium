@Dynamics365
Feature: Creating and Deleting an Account in Dynamics 365 Sales

  Background:

    Then User opens Url "https://thdynamics365sandbox.crm4.dynamics.com/"
    When User enters valid "AutoTestUser6@testhouse.net" and "Aft@0426" credentials and clicks the Sign In button
    And User clicks the Next button
    And User clicks the Skip link
   # And User clicks the Next button
    #And User clicks the Skip link

    And User click the Yes button to confirm
    Then User should be successfully logged in and navigated to the Dynamics 365 Sales Hub CRM page

  @CreateAccount
  Scenario Outline: Create a new account
    When User clicks on the "Accounts" button in the account
    When the User clicks on the "New" button to begin a new  account creation process
    And User clicks on the "OK" button in the account
    And User fills textfields in the account
      | Field                          | Value                    |
      | Account Name                   | <AccountName>            |
      | Address 1: Country/Region      | <CountryRegion>          |
      | Website                        | <Website>                |
      | Phone                          | <Phone>                  |
      | Fax                            | <Fax>                    |
      | Address 1: Street 1            | <Street1>                |
      | Address 1: City                | <City>                   |
      | Address 1: State/Province      | <StateProvince>          |
      | Address 1: County              | <County>                 |
      | Address 1: ZIP/Postal Code     | <PostalCode>             |
      | Address Phone                  | <AddressPhone>           |
      | Address 2: Street 1            | <Street2>                |
      | Address 2: City                | <City2>                  |
      | Address 2: State/Province      | <StateProvince2>         |
      | Address 2: County              | <County2>                |
      | Address 2: Country/Region      | <CountryRegion2>         |
      | Address 2: ZIP/Postal Code     | <PostalCode2>            |
      | Address 2: Telephone 1         | <Telephone1>             |
    And User fills dropdownfields in the account
      | Field    | Value      |
      | Industry | <Industry> |
    And User fills lookupfield in the account
      | Field           | Value      |
      | Parent Account  | <ParentAccountvalue> |
    And User clicks on the "Save & Close" button in the account
    Then User should see the new account saved successfully

    Examples:
      | AccountName | CountryRegion  |Website                | Phone       | Fax        | Street1        | City    | StateProvince | County | PostalCode | AddressPhone | Street2           | City2         | StateProvince2 | County2         | CountryRegion2 | PostalCode2 | Telephone1  | Industry   | ParentAccountvalue |
      | New Account | United States | www.newtestaccount.com | +1234567891 | +1234567891| 123 Main Street| Calicut | Kerala        | India  | 12345      | +1234567891  | 456 Second Street | Example City  | Example State   | Example County | Example Country| 67890       | +9876543210 | Wholesale  | ABC              |

  @FillBasicInfo
  Scenario Outline: Fill Basic Information for Account
    When User clicks on the "Accounts" button
    When the User clicks on the "New" button to begin a new  account creation process
    And User clicks on the "OK" button
    And User fills text in form fields
      | Field                          | Value                    |
      | Account Name                   | <AccountName>            |
      | Address 1: Country/Region      | <CountryRegion>          |
    And User fills textfields in the account
      | Field    | Value      |
      | Industry | <Industry> |
    When User clicks on the "Save & Close" button in the account
    Then User should see the new account saved successfully

    Examples:
      | AccountName | CountryRegion | Industry   |
      | New Account1| United States | Wholesale  |

  @DeleteAccount
  Scenario: Delete the created account
    When User clicks on the "Accounts" button
    And User clicks the Delete button
    Then User should be in the "Accounts My Active Accounts - Dynamics 365" page
