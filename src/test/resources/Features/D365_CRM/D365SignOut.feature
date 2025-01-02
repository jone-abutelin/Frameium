Feature: Sign out from Dynamics 365 Sales

  @SignOut
  Scenario: Sign out from Dynamics 365 Sales website

    Then User opens Url "https://thdynamics365sandbox.crm4.dynamics.com/"
    When User enters valid "AutoTestUser6@testhouse.net" and "Aft@0426" credentials and clicks the Sign In button
    And User clicks the Next button
    And User clicks the Skip link
    And User click the Yes button to confirm
    Then User should be successfully logged in and navigated to the Dynamics 365 Sales Hub CRM page
    When User clicks on the profile icon
    And User clicks on the sign out option
    Then User should be redirected to the sign out page
