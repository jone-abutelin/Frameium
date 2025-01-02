Feature: Skill based routing

  @createKnowledgeArticle
  Scenario Outline: Knowledge Article
    Then User opens Url "https://login.salesforce.com/?locale=in"
    Given I login with username "<username>" and password "<password>" of salesforce
    When I navigate to the app launcher and go to service console page
    Then I go to the Knowledge page
    And I click on New button
    Then I will create a new Knowledge article with "<title>" and "<url>" and verify the title

    Examples:
      | username             | password  | title          | url                             |
      | gt@testhouse.sandbox | Ethan@123 | Password Reset | https:knowledgebase/sf/reset575 |

  @EditKnowledgeArticle
  Scenario: Ensure that knowledge articles can be edited
    Given user is on created knowledge article screen
    When I click on edit
    And I am  able to edit answer field
    Then I click on save

  @KnowledgeArticleVersioning
  Scenario: Versioning of Knowledge Article
    When I click Edit button and navigate to the edit page
    And I make some changes and save it
    Then I publish and verify the incremented version number

  @publishKnowledgeArticle
  Scenario: Publish Knowledge Article
    Given user is on created knowledge article screen
    When I click on publish
    And I verify the Publish message
    When I Click on publish button
    Then I verify Publication Status

