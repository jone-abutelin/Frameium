Feature: Jira Issue Creation
  Background:
    Given The base URI of JIRA is "https://frameium-team.atlassian.net"
    And the header of JIRA is set

  @POSTIssue
  Scenario Outline: Create a new Jira issue
    When a "POST" request is sent to "/rest/api/3/issue" with payload from filepath "<filepath>"
    Then the status code of creating Issue in JIRA should be <status>

    Examples:
      | filepath                                           | status |
      | src/test/resources/Payloads/Jira_Issue/issue1.json | 201    |

  @GETIssue
  Scenario Outline: Verify Jira issue creation response contains a particular item
    When a "GET" request is sent to "<endpoint>" with payload from filepath "<filepath>"
    Then the API response of GET Issue in JIRA should contain the item "key" with value "DEMO-5"

    Examples:
      | filepath                                           | endpoint                 |
      | src/test/resources/Payloads/Jira_Issue/issue1.json | /rest/api/3/issue/DEMO-5 |

  @PUTIssue
  Scenario Outline: Update an existing Jira issue
    When a "PUT" request is sent to "<endpoint>" with payload from filepath "<filepath>"
    Then the status code of updating JIRA Issue should be <status>

    Examples:
      | filepath                                                 | endpoint                  | status |
      | src/test/resources/Payloads/Jira_Issue/issue_update.json | /rest/api/3/issue/DEMO-5  | 204    |

  @DELETEIssue
  Scenario Outline: Delete a Jira issue
    When a "DELETE" request is sent to "<endpoint>"
    Then the status code of deleting JIRA Issue should be <status>

    Examples:
      | endpoint                     | status |
      | /rest/api/3/issue/DEMO-1     | 204    |

  @JiraIssueManagement
  Scenario Outline: Create, read and update a Jira issue using api
    When user creates a new JIRA issue with details from "<createBody>"
    Then user retrieves the created JIRA issue
    Then user updates the created JIRA issue with details from "<updateBody>"

    Examples:
      | createBody                                         | updateBody                                               |
      | src/test/resources/Payloads/Jira_Issue/issue1.json | src/test/resources/Payloads/Jira_Issue/issue_update.json |




