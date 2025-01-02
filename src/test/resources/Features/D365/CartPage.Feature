@AllSwag
Feature: Cart Handling

  Scenario: Navigate to Cart
    Given Launch chrome browser
    Then User opens Url "https://www.saucedemo.com/"
    Given I login with the given username "standard_user" and password "secret_sauce"
    When I navigate to the cart
    Then I should be on the cart page

  Scenario: Verify Cart Empty Status
    Given I am in the cart page
    Then I check whether the cart is empty
