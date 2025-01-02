@Dynamics365
Feature: Lead Management in Dynamics 365 Sales using data from Excel

  Background:
    Given User opens Url "https://thdynamics365sandbox.crm4.dynamics.com/"

Feature: Lead Management in Dynamics 365 Sales

  Background:

    Then User opens Url "https://thdynamics365sandbox.crm4.dynamics.com/"
    When User enters valid "AutoTestUser6@testhouse.net" and "Aft@0426" credentials and clicks the Sign In button
    And User clicks the Next button
    And User clicks the Skip link
    And User click the Yes button to confirm
    Then User should be successfully logged in and navigated to the Dynamics 365 Sales Hub CRM page

  @CreateLead
  Scenario Outline: Create a new lead
    When User clicks on the "Leads" button
    When the User clicks on the "New" button to begin a new lead creation process
    And User fills text in form fields
      | Field                | Value                            |
      | Opportunity Name     | <OpportunityName>                |
      | First_Name(FN)       | <FirstName>                      |
      | Last_Name(LN)        | <LastName>                       |
      | Job Title            | <JobTitle>                       |
      | Business Phone       | <BusinessPhone>                  |
      | Fax                  | <Fax>                            |
      | Company              | <Company>                        |
      | Street 1             | <Street1>                        |
      | Street 2             | <Street2>                        |
      | Street 3             | <Street3>                        |
      | City                 | <City>                           |
      | State/Province       | <StateProvince>                  |
      | ZIP/Postal Code      | <PostalCode>                     |
      | Country              | <Country>                        |
      | Website              | <Website>                        |
      | Mobile Phone         | <MobilePhone>                    |
      | Email                | <LeadEmail>                      |

    And User fills dropdown in the form fields
      | Field                | Value                            |
      | Salutation           | <Salutation>                     |
      | Sales Region         | <SalesRegion>                    |
      | Service Line Detail  | <ServiceLineDetail>              |
      | Industry             | <Industry>                       |
      | Lead Source          | <LeadSource>                     |

    And User fills lookupfields in form
      | Field                | Value      |
      | Converted Account Id | <ConvertedAccountId> |
      | Converted Contact    | <ConvertedContact>   |
      | Converted Opportunity| <ConvertedOpportunity> |

    And User fills Select Date in form
      | Field                | Value            |
      | Converted Date       | <ConvertedDate>  |

    When User clicks on the "Save & Close" button
    Then the lead is created successfully
    Examples:
      | OpportunityName  | FirstName | LastName | JobTitle | BusinessPhone | Fax        | Company      | Street1      | Street2 | Street3        | City      | StateProvince | PostalCode | Country | Website         | Salutation | SalesRegion | ServiceLineDetail               | Industry     | LeadSource         | ConvertedAccountId  | ConvertedContact | ConvertedOpportunity | MobilePhone    | LeadEmail               | ConvertedDate |
      | Opportunity 16   | Albert    | Ram      | Manager  | 4239517991    | 1234577809 | Indiana Company  | 123 Test St  | Apt 101 | Nadapuram Road | Testville | CA            | 12350      | USA     | www.albertram.com | Mr.        | Australia   | Code audit/Security Code Review | Accountancy  | Internal referral  | ABC PVT LTD         | ABC5 D           | new mail response    | 89865430234     | albert34610@test.net      | 21/06/2004    |

  @DeleteLead
  Scenario: Delete the created Lead
    When User clicks on the "Leads" button
    And User clicks the Delete button
    Then User should be in the "Leads My Open Leads - Dynamics 365" page


  @QualifyLead
  Scenario Outline: Qualify the lead
    When User clicks on the "Leads" button
    When User clicks the "Lead Filter by keyword" and enter the '<FirstName>'
    When User double click the FirstName "<FirstName>"
    Then the user should be redirected to the "Lead: Lead: <FirstName> <LastName> - Dynamics 365"
    When User clicks the Qualification  button
    And User fills text in the qualification form fields
      | Field            | Value              |
      | Estimated Budget | <EstimatedBudget> |
      | Capture Summary  | <CaptureSummary>  |
    When User clicks on the "Close" button
    When User clicks on the "Qualify" button
    Then the user should be redirected to the Opportunity "Opportunity: Opportunity: <OpportunityName> - Dynamics 365" Page

    Examples:
      |FirstName | LastName |OpportunityName| EstimatedBudget | CaptureSummary                                                                                                     |
      |Albert    |    Ram      |Opportunity 16 | 1000000          | Lead: Albert from Indiana Corp, interested in $50K tech solution, scheduled demo after initial inquiry and follow-up.|

