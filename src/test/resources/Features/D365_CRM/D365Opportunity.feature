@Dynamics365
Feature: Working with the Opportunity in Dynamics 365 Sales

  Background:
    Then User opens Url "https://thdynamics365sandbox.crm4.dynamics.com/"
    When User enters valid "AutoTestUser6@testhouse.net" and "Aft@0426" credentials and clicks the Sign In button
    And User clicks the Next button
    And User clicks the Skip link
    And User click the Yes button to confirm
    Then User should be successfully logged in and navigated to the Dynamics 365 Sales Hub CRM page

  @NeedAnalysis_Process
  Scenario Outline:Update Opportunity Details for Need Analysis
    When User clicks on the "Opportunities" button in the opportunity
    And User selects the opportunity named "<OpportunityName>"
    Then User should see the "Opportunity: Opportunity: <OpportunityName> - Dynamics 365" details page
    When User updates textfields in opportunity form
      | Field                       | Value                      |
      | Next Step                   | <NextStep>                 |
      | Projected Profit Margin (%) | <ProjectedProfitMargin(%)> |
      | Current Situation           | <CurrentSituation>         |
      | Customer Need               | <CustomerNeed>             |
      | Proposed Solution           | <ProposedSolution>         |

    And User updates dropdownfields in opportunity form
      | Field         | Value          |
      | Business Type | <BusinessType> |
      | Industry      | <Industry>     |
      | Contact Role  | <ContactRole>  |
    And User updates lookupfields in opportunity form
      | Field                   | Value                   |
      | Referred By             | <ReferredBy>            |
      | Primary Partner account | <PrimaryPartneraccount> |
    And User Updates Select Dates in opportunity form
      | Field              | Value              |
      | Project Start Date | <ProjectStartDate> |
      | Project End Date   | <ProjectEndDate>   |
      | Actual Close Date  | <ActualCloseDate>  |
    And User clicks on the "More Header Editable Fields" button in the opportunity

    And User Updates Select Est. Close Date in opportunity form
      | Field           | Value           |
      | Est. Close Date | <Est.CloseDate> |
    And User clicks on the "More Header Editable Fields" button in the opportunity
    And User clicks the Needs Analysis button
    And User updates dropdownfields in opportunity form
      | Field                 | Value                 |
      | Identify Stakeholders | <Identifytakeholders> |

    And User select on the "Next Stage"
    Then User should see the updated opportunity details saved successfully

    Examples:
      | OpportunityName | NextStep                                                        | ProjectedProfitMargin(%) | CurrentSituation | CustomerNeed                                                     | ProposedSolution                                         | BusinessType | Industry   | ContactRole    | ReferredBy | PrimaryPartneraccount | ProjectStartDate | ProjectEndDate | ActualCloseDate | Est.CloseDate | Identifytakeholders |
      | Opportunity 201 | Follow up with a phone call to discuss customer needs in detail | 25                       | Develop          | They require a scalable system that can grow with their business | Ensure scalability to accommodate future business growth | New Business | Consulting | Decision Maker | Abhijith   | TestMega.com          | 01/07/2024       | 31/12/2024     | 15/06/2025      | 30/06/2025    | completed           |

  @Proposal&PriceQuote

  Scenario Outline: Update Opportunity Details for Proposal & Price Quote
    When User clicks on the "Opportunities" button in the opportunity
    And User selects the opportunity named "<OpportunityName>"
    Then User should see the "Opportunity: Opportunity: <OpportunityName> - Dynamics 365" details page
    And User clicks the Proposal & Price Quote button
    When User updates textfields in opportunity form
      | Field                     | Value                   |
      | No. of onsite resources   | <NoOfOnsiteResources>   |
      | No. of offshore resources | <NoOfOffshoreResources> |
    And User updates dropdownfields in opportunity form
      | Field                      | Value                      |
      | Develop Proposal           | <DevelopProposal>          |
      | Complete Internal Proposal | <CompleteInternalProposal> |
      | Present Proposal           | <PresentProposal>          |
    And User clicks on the "Next Stage" button
    Then the user should see the updated Proposal & Price Quote details saved successfully

    Examples:
      | OpportunityName | NoOfOnsiteResources | NoOfOffshoreResources | DevelopProposal | CompleteInternalProposal | PresentProposal |
      | Opportunity 201 | 12                  | 32                    | Completed       | Completed                | Completed       |


  @Negotiation&Review

  Scenario Outline: Update Opportunity Details for Negotiation & Review
    When User clicks on the "Opportunities" button in the opportunity
    And User selects the opportunity named "<OpportunityName>"
    Then User should see the "Opportunity: Opportunity: <OpportunityName> - Dynamics 365" details page
    And User clicks the Negotiation & Review button
    And User updates dropdownfields in opportunity form
      | Field                   | Value                   |
      | Complete Final Proposal | <CompleteFinalProposal> |

    And User clicks on the "Next Stage" button
    Then the user should see the updated Negotiation & Review  details saved successfully


    Examples:
      | OpportunityName | CompleteFinalProposal |
      | Opportunity 201 | Completed             |

  @Complete

  Scenario Outline: Update Opportunity Details to Completion
    When User clicks on the "Opportunities" button in the opportunity
    And User selects the opportunity named "<OpportunityName>"
    Then User should see the "Opportunity: Opportunity: <OpportunityName> - Dynamics 365" details page
    And User clicks the Completion button
    And User Updates Select Dates in opportunity form
      | Field                 | Value                 |
      | Confirm Decision Date | <ConfirmDecisionDate> |

    And User updates dropdownfields in opportunity form
      | Field          | Value          |
      | Send Thank You | <SendThankYou> |


    And User clicks on the "Finish" button
    Then the user should see that the opportunity details have been successfully saved and marked as completed.
    Examples:
      | OpportunityName | ConfirmDecisionDate | SendThankYou |
      | Opportunity 201 | 16/06/2025          | Completed    |