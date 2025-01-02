@AllSwag
Feature: About Page Functionality

  Scenario: Navigate to About Page
    Given Launch chrome browser
  	Then User opens Url "https://www.saucedemo.com/"
    Given I login with username "standard_user" and password "secret_sauce"
    When I navigate to the About page of swagdemo
    Then I should be on the About page

  Scenario: Verify About Page Title
    Given I am in the About page
    Then The About page title should be "Sauce Labs: Cross Browser Testing, Selenium Testing & Mobile Testing"
