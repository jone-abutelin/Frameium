Feature: Jira Issue Attachment
  Background:
    Given The base URI of JIRA is "https://frameium-team.atlassian.net"
    And the header of JIRA issue attachment is set

  Scenario Outline: Upload an attachment to a Jira issue
    And the additional headers are set
      | Header                   | Value                   |
      | X-Atlassian-Token        | no-check                |
    When a "POST" request for JIRA issue attachment is sent to "<endpoint>" with file "<filepath>"
    Then the response status code should be 200
    And the response should contain the JIRA attachment details

    Examples:
      | filepath                                | endpoint                             |
      | src/test/resources/Logo/testhouse3.png  | /rest/api/3/issue/DEMO-7/attachments |
