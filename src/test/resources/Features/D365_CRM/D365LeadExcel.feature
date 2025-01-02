@Dynamics365
Feature: Lead Management in Dynamics 365 Sales using data from Excel

  Background:
    Then User opens Url "https://thdynamics365sandbox.crm4.dynamics.com/"
    When User enters valid "AutoTestUser6@testhouse.net" and "Aft@0426" credentials and clicks the Sign In button
    And User clicks the Next button
    And User clicks the Skip link
   # And User clicks the Next button
   # And User clicks the Skip link
    And User click the Yes button to confirm
    Then User should be successfully logged in and navigated to the Dynamics 365 Sales Hub CRM page

  @LeadExcel
  Scenario Outline: Create a new lead
    When User clicks on the "Leads" button
    When the User clicks on the "New" button to begin a new lead creation process
    When User fills the form fields from Excel "<FilePath>" sheet "<SheetName>" module "<ModuleName>"
    When User clicks on the "Save & Close" button
    Then the lead is created successfully

    Examples:
      | FilePath                                                  | SheetName  | ModuleName   |
      | src/test/resources/D365_CRM-TestData/NewLeadCreation.xlsx | ValidTest1 | LeadCreation |

  @LeadExcelInvalid
  Scenario Outline: Attempt to create a lead with invalid data
    When User clicks on the "Leads" button
    When the User clicks on the "New" button to begin a new lead creation process
    When User fills the form fields from Excel "<FilePath>" sheet "<SheetName>" module "<ModuleName>"
    When User clicks on the "Save & Close" button
    Then the lead should not be created and an error message should be displayed

    Examples:
      | FilePath                                                  | SheetName    | ModuleName   |
      | src/test/resources/D365_CRM-TestData/NewLeadCreation.xlsx | InvalidTest1 | LeadCreation |

  @LeadQualification
  Scenario Outline: Qualify the lead using data from Excel
    When User clicks on the "Leads" button
    When User clicks the "Lead Filter by keyword" and enter the '<FirstName>'
    When User double click the FirstName "<FirstName>"
    Then the user should be redirected to the "Lead: Lead: <FirstName> <LastName> - Dynamics 365"
    When User clicks the Qualification  button
    When User fills the form fields from Excel "<FilePath>" sheet "<SheetName>" module "<ModuleName>"
    When User clicks on the "Close" button
    When User clicks on the "Qualify" button
    Then the user should be redirected to the Opportunity "Opportunity: Opportunity: <OpportunityName> - Dynamics 365" Page

    Examples:
      | FilePath                                                  | SheetName  | ModuleName  | FirstName | LastName | OpportunityName |
      | src/test/resources/D365_CRM-TestData/NewLeadCreation.xlsx | ValidTest1 | QualifyLead | Albin     | Ram      | Opportunity 17  |


