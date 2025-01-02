Feature: ClimateX Canada UI validation

  @ClimateXCanada
  Scenario Outline: Verify Accessibility For Canada CA
    Given Launch chrome browser
    Then User opens Url "<url>"
 
    Examples:
      | username     | password | year1 | year2 | url                                                      |
      | test1@mm.net | Test     | 2050  | 2020  | https://www.canada.ca/en/environment-climate-change.html |
     # | test1@mm.net | Test |  2050 |  2020 | https://www.canada.ca/en/employment-social-development/services/sin.html|
      #| test1@mm.net | Test     | 2050  | 2020  | https://www.canada.ca/en/services/health/food-nutrition.html|