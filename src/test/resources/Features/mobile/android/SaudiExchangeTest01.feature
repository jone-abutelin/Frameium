@MobileSaudiMobilePOC
Feature: Saudi Exchange MobileAutomation


  @TC01 @Pair
  Scenario: Verify Production Latest Market News  UI with UAT UI
    Given user navigates to "https://www.saudiexchange.sa/wps/portal/saudiexchange/home/"
    When use click on "Market News" from Latest News
    Then user fetch Production News UI text content and store it into Prod HashMap
    When user navigate to "https://www.saudiexchange.sa/wps/portal/saudiexchange/home/"
    When use click on "Market News" from Latest News
    Then user fetch UAT News UI text content and store it into Prod HashMap
    Then user compares Production and UATMarket News UI text content


  @TS019 @Pair
  Scenario: Verify TS019 Personalize function checkbox selection in Homepage
    Given user navigates to "https://www.saudiexchange.sa/wps/portal/saudiexchange/home/"
    When use click on Personalize button
    And User choose personalize options as "Transportation"
    And user click on Apply Selected button
    Then verify selected "Transportation" item is displayed on HomePage


  @DownloadTest
  Scenario: Market Report production Page comparison with Production
    #go to Prod site
    Given user navigates to "https://www.saudiexchange.sa/wps/portal/saudiexchange/newsandreports/reports-publications/market-reports?locale=en"
    When user click on Download button from Main Market and Document name is "Detailed Daily Report" and Environment is "Prod"


  @DownloadTest
  Scenario: Market Report production Page comparison with UAT
    #go to UAT site
    Given user navigates to "https://www.saudiexchange.sa/wps/portal/saudiexchange/newsandreports/reports-publications/market-reports?locale=en"
    When user click on Download button from Main Market and Document name is "Detailed Daily Report" and Environment is "UAT"


  @DownloadTest
  Scenario: Compare two zip files
    And Extract the "Prod" zip file and open it
    And Extract the "UAT" zip file and open it
    Then compare  "DetailedDaily_en.html" and "DetailedDaily_en.html" and HTML file extracted from Zip


