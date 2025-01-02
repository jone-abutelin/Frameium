Feature: ClimateX UI validation

  @ClimateX

  Scenario Outline: Verify PDF file and heatmap image comparison
    Given Launch chrome browser
    Then User opens ClimateX staging Url "http://staging-world.climate-x.com.s3-website.eu-west-2.amazonaws.com"
    Then Enter Username and Password
    Then Verify ClimateX home - Evaluate an asset page
    Then Click on Upload CSV button
    Then Import data from CSV file
    Then Select CSV file from File explorer
    Then Verify the file uploading status
    Then Select worksheet and click on Continue button
    Then Click on Review button
    Then Verify Excel file and uploaded data
    And Click on Continue
    Then Verify uploading is Done and Click on Ok button
    Then Verify popup Take me to the results and Click on Yes button
    #Validation of the data in Spectra platform with the pdf generated
    Then User verify project page "<username>"
    And User fetch risk scores based on year "<year1>", "<year2>"
    Then User generates pdf report
    And User extract text from pdf file
    And User compare risk scores of ui with pdf based on the years
    #HeatMap capture screeshot and do comparison
    Then Select Risk Filter and HeatMap should be displayed
    And Capture heat map image and compare map with previous date captured image
    Then User log out from ClimateX application

    Examples: 
      | username                               | password      | year1 | year2 |
      | abhirami.balasubramanian@testhouse.net | Testhouse@123 |  2050 |  2020 |
