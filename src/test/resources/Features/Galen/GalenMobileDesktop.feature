Feature: Header Layout Testing
@LayoutTesting
  Scenario Outline: Validate header layout on <device>
    Given open the example website "https://dev-robin-uae.santechture.com/UROBIN/faces/MainPages/SANTECHTURE/Login.xhtml"
    When check the header layout on "<device>"
    Then the header layout should match the "<device>" specifications

  Examples:
    | device   |
    | desktop  |
    | mobile   |
