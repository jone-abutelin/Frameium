Feature: Header layout validation
@VerifyingElements
Scenario: Validate the element on Robin login page
    Given Open the Robin login page  "https://dev-robin-uae.santechture.com/UROBIN/faces/MainPages/SANTECHTURE/Login.xhtml"
    When I check the elements on the login page
    Then the Elements should match the specifications