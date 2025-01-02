@Dynamics365 @CompetitorManagement @CompetitorAndContactManagement
Feature: Manage Competitors in Dynamics 365 Sales


  @CreateCompetitor   @ado_tc_id=114-115-116
    Then User opens Url "https://thdynamics365sandbox.crm4.dynamics.com/"
    When User enters valid "AutoTestUser6@testhouse.net" and "G.604663945958uc" credentials and clicks the Sign In button
    And User click the Yes button to confirm
    Then User should be successfully logged in and navigated to the Dynamics 365 Sales Hub CRM page
    When User clicks on the "Competitors" button
    When User enters valid "AutoTestUser6@testhouse.net" and "Aft@0426" credentials and clicks the Sign In button
    And User clicks the Next button
    And User clicks the Skip link
    And User click the Yes button to confirm
    Then User should be successfully logged in and navigated to the Dynamics 365 Sales Hub CRM page


  @CreateCompetitor

  Scenario Outline: Add a new competitor after logging in
    When User clicks on the "Competitors" button in the competitor
    When the User clicks on the "New" button to begin a new competitor creation process
    And User fills textfields in the competitor
      | Field                | Value            |
      | Competitors_Name(CN) | <CompetitorName> |
      | Website              | <Website1>       |
      | Street 1             | <Street1>        |
      | Street 2             | <Street2>        |
      | Street 3             | <Street3>        |
      | City                 | <City>           |
      | State/Province       | <StateProvince>  |
      | ZIP/Postal Code      | <PostalCode>     |
      | Country/Region       | <CountryRegion>  |
    When User clicks on the "Save & Close" button in the competitor
    Then User should see the competitor saved successfully

    Examples:
      | CompetitorName     | Website1        | Street1     | Street2 | Street3 | City    | StateProvince | PostalCode | CountryRegion |
      | Example Competitor | www.example.com | 123 Main St | Apt 2B  | ABc     | Anytown | ST            | 12345      | USA           |

  @DeleteCompetitor   @ado_tc_id=114-115-117
  Scenario: Delete the created Competitor
    When User clicks on the "Competitors" button
    And User clicks the Delete button
    Then User should be in the "Competitors All Competitors (summary) - Dynamics 365" page