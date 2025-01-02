Feature: Verify Items Under Menu - English

  @P1
  @VerifyingTextUnderOurMarket @Web01
  Scenario: Verify that items under Our Market > Main Market Section appears correctly
    Then User opens the Url "http://www.saudiexchange.sa/"
    Then Click on the Our Markets dropdown menu
    Then Verify Following Items appears under Our Market Section > Main Market Section:
      | Main Market                 |
      | Main Market Watch           |
      | Market Summary              |
      | Market Performance          |
      | Indices Performance         |
      | Opening/Closing Prices      |
      | Issuers Trading Information |

  @PageValidations
  @VeriyingTextsUnderListing
  Scenario: Verify that items under Listing > Become An Issuer Section Section appears correctly
    Then User opens the Url "http://www.saudiexchange.sa/"
    Then click on the our List dropdown menu
    Then Verify Following Items appears under Listing > Become An Issuer Section:
      | Become an Issuer              |
      | Listing Incentives            |
      | Listing Readiness Assessment  |
      | Listing Rules                 |
      | Listing Documents & Resources |
      | Company in Focus              |
      | Meeting Request               |

  @PageValidations
  @VeriyingTextsUnderListingNeg
  Scenario: Negative - Verify that items under Listing > Become An Issuer Section Section appears correctly
    Then User opens the Url "http://www.saudiexchange.sa/"
    Then click on the our List dropdown menu
    Then Verify Following Items appears under Listing > Become An Issuer Section:
      | Become a Issuer               |
      | Listing Incentives            |
      | Listing Readiness Assessment  |
      | Listing Rules                 |
      | Listing Documents & Resources |
      | Company in Focus              |
      | Meeting Request               |