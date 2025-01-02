@ThemePage
Feature: Theme Switching

  @ThemeChange
  Scenario: Verify that the CSS properties change after switching from light to dark theme
    Then User opens Url "https://www.tamm.abudhabi/"
    When User Closes the cookie tray by clicking on the Accept button
    Given User captures the header CSS properties for the light theme
    When User clicks on the Theme menu button to switch to Dark theme
    Then User captures the header CSS properties for the dark theme
    Then verify that the header CSS properties change after switching themes
