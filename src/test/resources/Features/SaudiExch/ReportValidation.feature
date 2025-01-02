@ReportsValidation
Feature: Validate HTML Reports

   @production
   @P7
  Scenario Outline: 001_Navigate to Production site and download the Market Report.
  	Then User opens Url "https://www.saudiexchange.sa/wps/portal/saudiexchange/newsandreports/reports-publications/market-reports?locale=en"
    Given I am on the Saudi Stock Exchange Market Reports page
    And I should download the detailed daily report successfully
    Then I should unzip the zipped folder to "<destination>" with custom name "<custom name>"
    Then User opens Url "<production HTML>"
    Then I should get the values of market summary from production HTML
    
  Examples:
    | destination                                       | custom name        | production HTML                                                      |
    | C:\Users\RijuJohn\Downloads\MarketReportSaudi | DetailedDaily.html | C:\Users\RijuJohn\Downloads\MarketReportSaudi\DetailedDaily.html |
    
 @UAT
   @P7
  Scenario Outline: 002_Navigate to UAT site and download the Market Report.
  	Then User opens Url "https://www.saudiexchange.sa/wps/portal/saudiexchange/newsandreports/reports-publications/market-reports?locale=en"
    Given I am on the Saudi Stock Exchange Market Reports page
    And I should download the detailed daily report successfully
    Then I should unzip the zipped folder to "<destination>" with custom name "<custom name>"
    Then User opens Url "<uat HTML>"
    Then I should get the values of market summary from UAT HTML
    
  Examples:
    | destination            | custom name              | uat HTML |
    | D:\SaudiExMarketReport | DetailedDailyReport.html | D:\SaudiExMarketReport\DetailedDailyReport.html |
    
 @compareHTML
   @P7
 	Scenario Outline: 003_Compare the report downloaded and unzipped from Production with the UAT report
    Then the downloaded and unzipped HTML contents of prod "<actual html>" should match UAT "<expected html>" 
    
  Examples:
    | actual html                                                          | expected html                                   |
    | C:\Users\RijuJohn\Downloads\MarketReportSaudi\DetailedDaily.html | D:\SaudiExMarketReport\DetailedDailyReport.html |
    
    
 @compareValues
 @P7
  Scenario: 004_Compare the values of market summary extracted from Production with the UAT report
    Then I should compare the values of market summary extracted from prod and UAT HTML
       
    