Feature: Verify Details of Graph In Home Page


  @GraphValidation
  @VerifyGraphsYAxis
  Scenario: Verify that graph y axis data in prod matches with uat
    When User opens the Url "https://www.saudiexchange.sa/wps/portal/saudiexchange/home/"
    Then User is in Saudi Exchange home page
    Then Move to graph and get YAxis data
    When User opens the Url "https://www.saudiexchange.sa/wps/portal/saudiexchange/home/"
    Then User is in Saudi Exchange home page
    Then Move to graph and get YAxis data for second site
    Then Compare graph yaxis data between two sites

  @GraphValidation
  @VerifyGraphsXAxis
  Scenario: Verify that graph x axis data in prod matches with uat
    When User opens the Url "https://www.saudiexchange.sa/wps/portal/saudiexchange/home/"
    Then User is in Saudi Exchange home page
    Then Move to graph and get XAxis data
    When User opens the Url "https://www.saudiexchange.sa/wps/portal/saudiexchange/home/"
    Then User is in Saudi Exchange home page
    Then Move to graph and get XAxis data for second site
    Then Compare graph xaxis data between two sites

  @GraphValidation
  @VerifyGraphsData
  Scenario: Verify that graph data points in prod matches with uat
    When User opens the Url "https://www.saudiexchange.sa/wps/portal/saudiexchange/home/"
    Then User is in Saudi Exchange home page
    Then Get the highcharts-tracker-line data in Graph
    When User opens the Url "https://www.saudiexchange.sa/wps/portal/saudiexchange/home/"
    Then User is in Saudi Exchange home page
    Then Get the highcharts-tracker-line data in Graph for second site
    Then Compare graph data points between two sites








