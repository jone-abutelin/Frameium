Feature: Manage Test Cases and Cycles in Zephyr Scale

  Background:
    Given the API base URL is "https://api.zephyrscale.smartbear.com/v2"
    And the API token is set
    And the test cases payload is loaded from "src/test/resources/Payloads/Jira_Issue/testcases.json"
    And the test cycle payload is loaded from "src/test/resources/Payloads/Jira_Issue/testCycle.json"
    And the test execution payload is loaded from "src/test/resources/Payloads/Jira_Issue/testExecution.json"


  @testCase
  Scenario: Create test scenarios, test cases and add test steps to Zephyr from JSON payload
    When User creates test cases and add test steps in Zephyr

  @testCycle
  Scenario: Create test cycle in Zephyr scale
    Then User creates test cycle in Zephyr

  @linkIssue
  Scenario: Link a JIRA issue to a test cycle in Zephyr
    When User link the JIRA issue with ID "10003" to the Zephyr test cycle with ID "30521416"

  @testExecution
  Scenario: Add test cases to the Zephyr test cycle
    Then User link the testcases "FR-T6, FR-T8" to the zephyr test cycle with ID "FR-R5"