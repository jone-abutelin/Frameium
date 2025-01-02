Feature: Restaurant App UI validation


  Scenario: Contact Support 
    Given Launch chrome browser
    Then User opens Url "http://localhost:5173/"
    Then Click Contact In Header
    Then Check Contact Page Title
    Then Enter Customer Name
    Then Enter Customer Email
    Then Enter Customer Phone
    Then Enter Message
    Then Click Submit Option
    Then Click Home In Header
    Then Click Order Online

