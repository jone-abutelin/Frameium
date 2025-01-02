Feature: OCR Image Validation

  @SimpleText
  Scenario: Extract text from an image
    Given the OCR page is loaded with image "SimpleImage.jpg" and language "eng"
    When I perform OCR on the image
    Then the extracted text should not be empty

  @TextWithBackgroundImage
  Scenario: Extract text from a book page image
    Given the OCR page is loaded with image "images11.jpg" and language "eng"
    When I perform OCR on the image
    Then the extracted text should not be empty

  @DataTableImage
  Scenario: Extract text from a data table image
    Given the OCR page is loaded with image "DataTable.jpg" and language "eng"
    When I perform OCR on the image
    Then the extracted text should not be empty

  @BookPageImage
  Scenario: Extract text from a book page image
    Given the OCR page is loaded with image "bookPage2.jpg" and language "eng"
    When I perform OCR on the image
    Then the extracted text should not be empty

  @MultiLanguageImage
  Scenario: Extract text from an image containing multiple languages
    Given the OCR page is loaded with image "MultiLanguageImage.jpg" and language "mal"
    When I perform OCR on the image
    Then the extracted text should not be empty
    Given the OCR page is loaded with image "MultiLanguageImage.jpg" and language "eng"
    When I perform OCR on the image
    Then the extracted text should not be empty
  @GIFImage
  Scenario: Extract text from an GIF image
    Given the OCR page is loaded with gif image "GIF_Image.gif" and language "eng"
    When I perform OCR on the image
    Then the extracted text should not be empty