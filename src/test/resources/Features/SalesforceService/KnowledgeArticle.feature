Feature: Knowledge Management
  @KnowledgeArticle
  Scenario Outline: Knowledge Article
    Then User opens Url "https://testhouse2-dev-ed.develop.my.salesforce.com"
    Given I login with username "<username>" and password "<password>" of salesforce
    When I navigate to the app launcher and go to service console page
    Then I go to the Knowledge page
    And I click on New button
    Then I will create a new Knowledge article with "<title>" and "<url>" and verify the title


    Examples:
      | username             | password  | title          | url                             |
      | gt@testhouse.sandbox | Ethan@123 | Password Reset | https:knowledgebase/sf/reset575 |

