Feature: ClimateX UI validation

  @ClimateX

  Scenario Outline: Verify PDF file and heatmap image comparison
    Given Launch chrome browser
    Then User opens ClimateX staging Url "http://staging-world.climate-x.com.s3-website.eu-west-2.amazonaws.com"
    Then Enter a "<username>" and "<password>"
    Then Invalid user message appears


    Examples: 
      | username        | password      | year1 | year2 |
      | test1@mm.net | Test |  2050 |  2020 |
