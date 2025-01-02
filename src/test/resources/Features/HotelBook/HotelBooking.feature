Feature: Hotel booking scenario

  Scenario Outline: Hotel booking
    Given I am on the hotel booking page with username "<Username>" and password "<Password>"
    When I enter details about guest
    Then I must be able to book hotel tickets

    Examples:
      | Username           | Password           |
      | dsklhfj@gmail.com  | dsklhfj@gmail.com1 |