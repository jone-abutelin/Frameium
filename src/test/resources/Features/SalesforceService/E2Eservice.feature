Feature: E2E Service

  @LoginSalesforce
  Scenario Outline: Login to salesforce using credentials
    Then User opens Url "https://testhouse2-dev-ed.develop.my.salesforce.com"
    Given I am on the Salesforce login page
    When I enter my username "<username>" and password "<password>"
    And I click on the login button
    Then I should be logged in to Salesforce successfully

    Examples:
      | username             | password      |
      | gt@testhouse.sandbox | testhouse@123 |

  @caseCreate
  Scenario Outline: Creation of a new case
    When I navigate to the app launcher and go to service console page
    Then I go to the case page
    And I click on new case button
    Then I will create a new case with "<contactname>" and "<accountname>" and verify it

    Examples:
      | contactname | accountname           |
      | Jane Doe    | Global Tech Solutions |

  @EditCase
  Scenario Outline: Edit case owner
    When I click on Edit Case Owner
    And I remove Owner
    And I click Users
    And I select Queues from dropdown
    When I search for Owner "<ownername>"
    And I click on Owner from dropdown and save it
    Then I verify Case Owner

    Examples:
      | ownername          |
      | platinum           |

  @caseAutoAssignmentCreate
  Scenario Outline: creation of a new case with auto-assignment
    Then I go to the case page
    And I click on new case button
    And I will create a new case with "<contactname>" and "<accountname>" with auto-assignment and verify it
    Then I will verify the auto assigned case owner
    Examples:
      | contactname | accountname           |  type      | reason       |
      | Jane Doe    | Global Tech Solutions | Mechanical | Installation |

  @skillBasedRouting
  Scenario Outline: Skill based routing of a case
    Then I will go to the case page and create a new case  with "<contactname>", "<accountname>", "<type>" and "<reason>"
    And I will change the owner name to "<ownername>"
    And I will go to the omnichannel to made the status available
    Then I change the owner and select the queue "<queuename>" that is configured for routing based on skills
    Then I Verify that case is routed to right skilled agent based on type "<type>" and reason "<reason>"


    Examples:
      | contactname | accountname           | type       | reason    |ownername   | queuename   |
      | Jane Doe    | Global Tech Solutions | Electronic | Breakdown |Thomas Arun | AutoRouting |

  @acceptCase
  Scenario: Accept routed case from omnichannel widget
    Given I am on created case page
    Then I navigate to omnichannel widget
    And I Verify that omnichannel widget is updated with the routed case
    Then I Click on accept the case

  @OmniChannel
  Scenario: Verify Case Details on Omni Supervisor screen
    And I navigate to Omni Supervisor page
    And I navigate to Assigned Work
    Then I verify the AutoRouting Queue
    When I click on Queue
    Then I verify Case Details

  @CreateKnowledgeArticle
  Scenario Outline: Creation of knowledge Article
    Then I go to the Knowledge page
    And I click on New button
    And I will create a new Knowledge article with "<title>" and "<url>"
    Then I verify the created knowledge with "<title>"

    Examples:
      | title              | url                                 |
      | Password Reset     | https:knowledgebase/sf/reset575     |

  @EditKnowledgeArticle
  Scenario: Ensure that knowledge articles can be edited
    Given user is on created knowledge article screen
    When I click on edit
    And I am  able to edit answer field
    Then I click on save

  @PublishKnowledgeArticle
  Scenario: Publish Knowledge Article
    Given user is on created knowledge article screen
    When I click on publish
    And I Click on publish button
    Then I verify the Publish message
    And I verify Publication Status

  @KnowledgeArticleVersioning
  Scenario: Versioning of Knowledge Article
    When I click Edit button and navigate to the edit page
    And I make some changes and save it
    Then I publish and verify the incremented version number

  @CaseResolution
  Scenario Outline: Resolving the case using knowledge article created
    When I open the case to  be resolved
    And I search for knowledge using keyword "<keyword>" and select matching article "<article>"
    Then I navigate to the article
    And I update the case status
    Then I click on save button

    Examples:
      | keyword  | article        |
      | password | Password Reset |

  @KnowledgeArticleArchival
  Scenario: Knowledge Article Archival
    When I go to the published article
    And I click on Archive button
    Then I verify publication status

