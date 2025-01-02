@Dynamics365 @ContactManagement @CompetitorAndContactManagement
Feature: Contact Management in Dynamics 365 Sales

  @CreateContact  @ado_tc_id=118
  Scenario Outline: User creates a new contact with details
    Then User opens Url "https://thdynamics365sandbox.crm4.dynamics.com/"
    When User enters valid "AutoTestUser6@testhouse.net" and "Aft@0428" credentials and clicks the Sign In button
    When User enters valid "AutoTestUser6@testhouse.net" and "Aft@0426" credentials and clicks the Sign In button
    And User clicks the Next button
    #And User clicks the Skip link
    #And User click the Yes button to confirm
    Then User should be successfully logged in and navigated to the Dynamics 365 Sales Hub CRM page
    When User clicks on the "Contacts" button in the contact
    When the User clicks on the "New" button to begin a new contact creation process
    And User fills textfields in the contact
      | Field                      | Value                |
      | First Name                 | <FirstName>          |
      | Last Name                  | <LastName>           |
      | Job Title                  | <JobTitle>           |
      | Department                 | <Department>         |
      | Email                      | <Email>              |
      | Business Phone             | <BusinessPhone>      |
      | Mobile Phone               | <MobilePhone>        |
      | Fax                        | <Fax>                |
      | Hobbies and Interests      | <HobbiesInterests>   |
      | Assistant                  | <Assistant>          |
      | Assistant Phone            | <AssistantPhone>     |
      | Home Phone                 | <HomePhone>          |
      | Address 1: Street 1        | <Address1Street1>    |
      | Address 1: City            | <Address1City>       |
      | Address 1: State/Province  | <Address1State>      |
      | Address 1: Country/Region  | <Address1Country>    |
      | Address 1: ZIP/Postal Code | <Address1PostalCode> |
      | Address 1: Phone           | <Address1Phone>      |
      | Address 2: Street 1        | <Address2Street1>    |
      | Address 2: City            | <Address2City>       |
      | Address 2: State/Province  | <Address2State>      |
      | Address 2: Country/Region  | <Address2Country>    |
      | Address 2: ZIP/Postal Code | <Address2PostalCode> |
      | Address 2: Phone           | <Address2Phone>      |
    And User fills dropdownfields in the contact
      | Field             | Value             |
      | Salutation        | <Salutation>      |
      | Lead Source       | <LeadSource>      |
      | Role              | <Role>            |
      | Primary Time Zone | <PrimaryTimeZone> |
    And Contact User fills lookupfields in the contact
      | Field        | Value         |
      | Reports To   | <ReportsTo>   |
      | Account Name | <AccountName> |
    When User clicks on the "Save & Close" button in the contact
    Then New contact should be successfully created

    Examples:
      | FirstName | LastName | JobTitle | Department    | Email            | BusinessPhone | MobilePhone | Fax        | HobbiesInterests | Assistant | AssistantPhone | HomePhone  | Address1Street1 | Address1City | Address1State | Address1Country | Address1PostalCode | Address1Phone | Address2Street1   | Address2City | Address2State | Address2Country | Address2PostalCode | Address2Phone | Salutation | LeadSource        | Role     | PrimaryTimeZone   | ReportsTo   | AccountName |
      | Arathi    | Ramesh   | Manager  | IT Department | arathi@gmail.com | 3456789012    | 9876543210  | 1234567890 | Reading          | Jane      | 1234567890     | 9876543222 | 123 Main Street | Calicut      | Kerala        | India           | 12346              | +1234567892   | 456 Second Street | Example City | Example State | Example Country | 67895              | +9876543222   | Mr.        | External Referral | Employee | (GMT+13:00) Samoa | ABC PVT LTD | Abhijith    |

  @DeleteContact  @ado_tc_id=119
  Scenario: Delete the created Contact
    When User clicks on the "Contacts" button
    And User clicks the Delete button
    Then User should be in the "Contacts My Active Contacts - Dynamics 365" page
