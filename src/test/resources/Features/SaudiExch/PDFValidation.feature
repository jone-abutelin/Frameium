
Feature: Validate PDF Reports

 @prod
   @PDFValidation
  Scenario Outline: 001_Navigate to Production site and download the Monthly Report.Extract images and graphs.
  	Then User opens Url "https://www.saudiexchange.sa/wps/portal/saudiexchange/newsandreports/reports-publications/annual-reports?locale=en"
    Given I am on the Saudi Stock Exchange Annual Reports page
    Then I should see a list of annual reports
    When I select a specific annual report for "<year>", "<month>", "<report type>"    
    Then the annual report of prod should be downloaded successfully to "<downloads>"
    And I copy the downloaded report from "<downloads>" to new folder "<new folder>" with custom name "<custom name>"
    Then User opens Url "<actual pdf>"
    Then I should extract image from the PDF "<actual pdf>" to "<Image location>"
    Then I should extract bar graph from the PDF "<actual pdf>" to "<barGraph actual image>"
    
 
    Examples:
      | year | month   | report type       | downloads                         | barGraph expected image   | barGraph actual image                               | Image location                                  | new folder                                 | actual pdf                                            | custom name | 
      | 2024 | February| Monthly Reports   | C:/Users/RijuJohn/Downloads/  | D:/Bar Graph/BarGraph.png | C:/Users/RijuJohn/Downloads/Bar Graph/Graph.png | C:/Users/RijuJohn/Downloads/Image/image.png | C:/Users/RijuJohn/Downloads/ActualPdf/ | C:/Users/RijuJohn/Downloads/ActualPdf/SaudiEx.pdf | SaudiEx.pdf |
   
   
 @uat
   @PDFValidation
  Scenario Outline: 002_Navigate to UAT site and download the Monthly Report.Extract images and graphs. 
  	Then User opens Url "https://www.saudiexchange.sa/wps/portal/saudiexchange/newsandreports/reports-publications/annual-reports?locale=en"
    Given I am on the Saudi Stock Exchange Annual Reports page
    Then I should see a list of annual reports
    When I select a specific annual report for "<year>", "<month>", "<report type>"    
    Then the annual report of UAT should be downloaded successfully to "<download>"
    And I copy the downloaded report from "<download>" to new folder "<new folder>" with custom name "<custom name>"
    Given Launch chrome browser
    Then User opens Url "<expected pdf>"
    Then I should extract image from the PDF "<expected pdf>" to "<imageExpected location>"
    Then I should extract bar graph from the PDF "<expected pdf>" to "<barGraph expected image>"
       
    
    Examples:
      | year | month      | report type     | new folder      | expected pdf                      | actual pdf                                            | download                         | custom name        | imageExpected location   | barGraph expected image   |  
      | 2024 | February   | Monthly Reports | D:/ExpectedPdf/ | D:/ExpectedPdf/SaudiExchange.pdf  | C:/Users/JoneAbutelin/Downloads/ActualPdf/SaudiEx.pdf | C:/Users/RijuJohn/Downloads/ | SaudiExchange.pdf  | D:/ImageExpected/Img.png | D:/Bar Graph/BarGraph.png |
  
    @comparePdf
      @PDFValidation
    @skipafter
 	Scenario Outline: 003_Compare the report downloaded from Production with the UAT report
    Then the downloaded PDF contents of prod "<actual pdf>" should match UAT "<expected pdf>"
    
    Examples:
     | expected pdf                     | actual pdf                                             |                                     
     | D:/ExpectedPdf/SaudiExchange.pdf | C:/Users/RijuJohn/Downloads/ActualPdf/SaudiEx.pdf  |
  
  @compareBarGraph
    @PDFValidation
    @skipafter
 	Scenario Outline: 004_Compare the graph images extracted from Production PDF with UAT PDF.
    Then I should compare extracted bar graph from prod "<barGraph actual image>" and UAT "<barGraph expected image>"
  
    Examples:
   | barGraph expected image   | barGraph actual image                               | 
   | D:/Bar Graph/BarGraph.png | C:/Users/RijuJohn/Downloads/Bar Graph/Graph.png |
  
    
  @compareImage
    @PDFValidation
    @skipafter
   	Scenario Outline: 005_Compare the general images extracted from Production PDF with UAT PDF.
     Then I should compare extracted image from prod "<Image location>" and UAT "<imageExpected location>"
    
    Examples:
     | Image location                                  | imageExpected location    |                                     
     | C:/Users/RijuJohn/Downloads/Image/image.png | D:/ImageExpected/Img.png  |
   

  @reportVerification
    @PDFValidation
  Scenario Outline: 006_Verify Arabic and English text appear correctly in specific page of PDF
    Given Launch chrome browser
    Then User opens Url "C:/Users/RijuJohn/Downloads/2024_02__REPORT.pdf"
    Given I am on the PDF report file of Saudi Exchange
    Then I should verify that the text appears correctly on page 3
    Then I should verify that the Arabic text "<arabic text>" appears correctly on page 3
    Then I should extract tables from the PDF
       
  Examples:   
     | arabic text      |
     |  بانخفاض            |
     
  @negativeArabicText
    @PDFValidation
  Scenario Outline: 007_Verify negative Arabic text in pdf_Test fails
    Given Launch chrome browser
    Then User opens Url "C:/Users/RijuJohn/Downloads/2024_02__REPORT.pdf"
    Given I am on the PDF report file of Saudi Exchange
    Then I should verify that the negative Arabic text "<arabic text>" is not present on page 3
       
  Examples:   
     | arabic text      |
     |  تكنوبارك            |
     