Feature: API Testing with GET Requests

  Background:
    Given The base URI is "https://reqres.in/"

  Scenario Outline: Retrieve User Information
    When a "GET" request is sent to the base URI with end point "api/users/2"
    Then I should get the following keys "<key>" and values "<value>" from the json response

    Examples:
      | key          | value                     |
      | id           | 2                         |
      | email        | janet.weaver@reqres.in    |
      | first_name   | Janet                     |
      | last_name    | Weaver                    |