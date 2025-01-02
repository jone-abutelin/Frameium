Feature: Archival of Knowledge Articles
  @KnowledgeArticleArchival
  Scenario Outline: Knowledge Article Archival
    Then User opens Url "https://testhouse2-dev-ed.develop.my.salesforce.com"
    Given I login with username "<username>" and password "<password>" of salesforce
    When I navigate to the app launcher and go to service console page
    Then I go to the published article
    And I click on Archive button
    Then I verify publication status

    Examples:
      | username             | password  |
      | gt@testhouse.sandbox | testhouse@123 |