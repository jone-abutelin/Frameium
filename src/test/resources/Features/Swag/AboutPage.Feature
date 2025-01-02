@AllSwag @AboutSwag
Feature: About Page Functionality
  @AboutSwag1
  Scenario: Navigate to About Page
  	Then User opens Url "https://www.saucedemo.com/"
    Given I login with username "standard_user" and password "secret_sauce"
    When I navigate to the About page of swagdemo
    Then I should be on the About page


  @AboutSwag2
  Scenario: Navigate to About Page
    Then User opens Url "https://www.saucedemo.com/"
    Given I login with username "standard_user" and password "secret_sauce"
    When I navigate to the About page of swagdemo
    Then I should be on the About page

#  Scenario: Verify About Page Title
#    Given I am in the About page
#    Then The About page title should be "Sauce Labs: Cross Browser Testing, Selenium Testing & Mobile Testing"
