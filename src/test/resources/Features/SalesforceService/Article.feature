Feature: Knowledge Management
  @CreateKnowledgeArticle
  Scenario Outline: Creation of knowledge Article
    Then User opens Url "https://testhouse2-dev-ed.develop.my.salesforce.com"
    Given I login with username "<username>" and password "<password>" of salesforce
    When I navigate to the app launcher and go to service console page
    Then I go to the Knowledge page
    And I click on New button
    Then I will create a new Knowledge article with "<title>" and "<url>" and verify the title

    Examples:
      | username             | password      | title              | url                                 |
      | gt@testhouse.sandbox | testhouse@123 | Password Reset     | https:knowledgebase/sf/reset575     |

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

  @KnowledgeArticleVersioning
  Scenario: Versioning of Knowledge Article
    When I click Edit button and navigate to the edit page
    And I make some changes and save it
    Then I publish and verify the incremented version number