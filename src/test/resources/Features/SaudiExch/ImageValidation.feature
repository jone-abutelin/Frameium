@VisualImage
Feature: ImageValidation
  I am Comparing the Image on Home Page

  @prodImage
  Scenario Outline: Downloading image from Production and saving
    Then User opens Url "https://www.saudiexchange.sa/"
    When I am on the home page of SaudiExchange in production for image
    Then I go to image and copy image by giving name as the <name> and I save the Image from production
    Examples:
      | name                 |
      | "image1OnRUpro"      |
      | "image1OnLookingpro" |

  @uatImage
  Scenario Outline: Downloading image from uat and Comparing_Image of both production and Uat
    Then User opens Url "https://www.saudiexchange.sa/"
    When I am on the home page of SaudiExchange in UAT for image
    Then I go to image and copy image by giving name as the <ExpectedImage> and I save the Image from uat
    Then I Compare the <ActualImage> and <ExpectedImage> both the images must be same and if it is different then it should create a differenceImage by name <DifferenceImage>
    Examples:
      | ActualImage           | ExpectedImage    | DifferenceImage |
      | "image1OnRUpro"       | "image1OnRUuat"  | "image1diff"    |
      | "image1OnLookingpro"  | "image1OnRUuatd" | "image2diff"    |




