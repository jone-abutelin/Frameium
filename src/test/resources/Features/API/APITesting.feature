Feature: API Testing
  Background:
    Given the base URI is "https://reqres.in/"

  Scenario Outline: Perform API Request
    When A "<method>" request is sent to "<endpoint>" with payload from filepath "<filepath>"
    Then the status code should be <status>

    Examples:
      | method | endpoint           | filepath                              | status |
      | GET    | api/users?page=2   |                                       | 200    |
      | POST   | api/login          | src/test/resources/Payloads/api3.json | 400    |
      | PUT    | api/users/2        | src/test/resources/Payloads/api2.json | 200    |
      | GET    | users              |                                       | 404    |
      | DELETE | api/users/2        |                                       | 204    |
      | POST   | api/register       | src/test/resources/Payloads/api1.json | 200    |

  Scenario Outline: Perform API Request
    When A "<method>" request is sent to "<endpoint>" with payload from filepath "<filepath>"
    Then I should get the response

    Examples:
      | method | endpoint          | filepath                              |
      | GET    | api/users?page=2  |                                       |
      | POST   | api/login         | src/test/resources/Payloads/api3.json |
      | PUT    | api/users/2       | src/test/resources/Payloads/api2.json |
      | GET    | users             |                                       |
      | DELETE | api/users/2       |                                       |
      | POST   | api/register      | src/test/resources/Payloads/api1.json |

  Scenario Outline: Verify API response contains a particular item
    When A "<method>" request is sent to "<endpoint>" with payload from filepath "<filepath>"
    Then the API response should contain the item <item>

    Examples:
      | method | endpoint          | filepath                              | item         |
      | GET    | api/users?page=2  |                                       | "data"       |
      | POST   | api/login         | src/test/resources/Payloads/api3.json | "error"      |
      | PUT    | api/users/2       | src/test/resources/Payloads/api2.json | "updatedAt"  |
      | POST   | api/register      | src/test/resources/Payloads/api1.json | "id"         |
      | DELETE | api/users/2       |                                       | "id"         |
