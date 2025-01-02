Feature: API Validation

@CarreyFourWebFullBurn
Scenario Outline: Carrefour Web Full Burn- transaction using points only
    Given User has access token generated
    And Provide payload for Carrefour Web Full Burn
    Then Execute POST Request for Carrefour Web Full Burn "<parameter1>" and "<parameter2>"
    And Validate response status code is 201

     Examples: 
      | parameter1  | parameter2 |
      | https://maf-holding-dev.apigee.net | /v1/gravty/bit/request      |

 @CarrefourWebFullEarn
Scenario Outline: Carrefour Web Full Earn
    Given User has access token generated
    And Provide payload for Carrefour Web Full Earn
    Then Execute POST Request for Carrefour Web Full Earn "<parameter1>" and "<parameter2>"
    And Validate response status code for Carrefour Web Full Earn 201

     Examples: 
      | parameter1  | parameter2 |
      | https://maf-holding-dev.apigee.net | /v1/gravty/bit/request      |
 
 
 @CarrefourWebSplitPay
Scenario Outline: Carrefour Web Split Pay
    Given User has access token generated
    And Provide payload for Carrefour Web Split Pay
    Then Execute POST Request for Carrefour Web Split Pay "<parameter1>" and "<parameter2>"
    And Validate response status code for Carrefour Web Split Pay 201

     Examples: 
      | parameter1  | parameter2 |
      | https://maf-holding-dev.apigee.net | /v1/gravty/bit/request      |
 

