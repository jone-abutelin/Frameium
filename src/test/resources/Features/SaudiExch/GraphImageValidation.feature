@GraphDifference
Feature: graphValidation
  I am Comparing the graph on Home Page

  @prodGraphss
  Scenario Outline: Downloading graph from Production and saving
    Then User opens Url "https://www.saudiexchange.sa/"
    When I am on the home page of SaudiExchange in production for graph
    Then I go to graph and copy graph by giving name as the <name> and I save the Graph_Image from production
    Examples:
      | name            |
      | "graphmainpro"  |
      | "graphmainpro2" |

  @uatGraph
  Scenario Outline: Downloading graph from uat and Comparing_Image of both production and Uat and it should be match
    Then User opens Url "https://www.saudiexchange.sa/"
    When I am on the home page of SaudiExchange in UAT for graph
    Then I go to graph and copy graph by giving name as the <ExpectedGraph> and I save the Graph_Image from uat
    Then I Compare the <ActualGraph> and <ExpectedGraph> both the graph must be same and  if it is different then it should create a differenceGraphImage by name <DifferenceGraphImage>
    Examples:
      | ActualGraph     | ExpectedGraph      | DifferenceGraphImage |
      | "graphmainpro"  | "graphmainuat"     | "graph1diff"         |
      | "graphmainpro2" | "graphparalleluat" | "graph2diff"         |


