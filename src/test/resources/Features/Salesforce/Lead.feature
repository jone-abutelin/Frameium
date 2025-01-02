Feature: Lead functionality
  @leadCreate
  Scenario Outline: creation of a new lead
    Then User opens Url "https://login.salesforce.com/?locale=in"
    Given I login with username "<username>" and password "<password>" of salesforce
    When I navigate to the app launcher and go to sales page
    Then I go to the leads page
    And I click on new button
    Then I will create a new lead and verify it
    
    Examples:
       | username           | password     |
       | jone@testhouse.com | Haniya@1  |

  Scenario Outline: Creation of a new Task from Lead
    Given I am on the created Lead's detail page
    When I click on the New Task button
    Then the Task creation form should be displayed with Lead data pre-filled
    And I fill in the task details with "<Subject>" and "<Due Date>" and save it
    Then the task should be created successfully

    Examples:
      | Subject         | Due Date      |
      | Call            | 27-April-2025 |
      
  Scenario Outline: Lead Progression
    Given I am on the created Lead's detail page
    When I click on the Details and change the lead status to "<Change lead Status>"
    Then the working-contacted lead status window should be opened
    And I update the details in that page and click save
    Then I click on the converted and select converted status button

    Examples:
      | Change lead Status  |
      | Working - Contacted |

  Scenario: Lead status conversion
    Given I am on the lead's detail conversion screen
    When I click on the Convert button
    Then the lead should be converted and accounts, contacts, and opportunities should be automatically created
    And close the lead converted window

