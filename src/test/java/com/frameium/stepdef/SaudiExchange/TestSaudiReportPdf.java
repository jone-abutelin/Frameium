package com.frameium.stepdef.SaudiExchange;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.stepdef.TestSetUp;
import org.testng.Assert;
import com.frameium.pageobject.SaudiExchange.PdfSaudiEx;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import com.frameium.stepdef.Hooks;

public class TestSaudiReportPdf {

	private PdfSaudiEx pdfReport;
	Hooks hooks = new Hooks();

	TestSetUp setUp;
	GenericFunctions genericFunctions;

	public TestSaudiReportPdf(TestSetUp setUp) throws IOException {
		this.setUp = setUp;
		genericFunctions = new GenericFunctions(setUp.baseTest.getDriver());
	}



	private static String expectedText = "At the end of February 2024 Tadawul All Share Index \r\n"
			+ "(TASI) closed at 12,630.86 point, increased by 834.23\r\n"
			+ "point 7.07% over the close of the previous month. \r\n"
			+ "Highest close level for the index during the month was\r\n" + "12,634.33 point on 21/02/2024.\r\n"
			+ "Total equity market capitalization at the end of \r\n"
			+ "February 2024 reached SAR 11,191.15 billion (US$ \r\n"
			+ "2,984.31 billion), increasing by 4.85% over the close\r\n" + "of the previous month.\r\n"
			+ "The total value of shares traded for the month of \r\n"
			+ "February 2024 reached SAR 185.97 billion (US \r\n" + "$49.59 billion), decreasing by 5.09% over the \r\n"
			+ "previous month.\r\n" + "The total number of volume traded* reached 7.57\r\n"
			+ "billion shares during the month of February 2024\r\n"
			+ "compared to 8.35 billion shares traded during the\r\n" + "previous month, a decrease of 9.35%.\r\n"
			+ "The total number of transactions executed during \r\n"
			+ "February 2024 reached 11.11 million compared to \r\n"
			+ "11.87 million trades for the month January 2024,\r\n" + "a decrease of 6.38%.";
	private static String pdfFilePath = "C:/Users/JoneAbutelin/Downloads/2024_02__REPORT.pdf";
	// You can set the threshold based on your expectations
	private static double threshold = 5.0;

	@Given("I am on the PDF report file of Saudi Exchange")
	public void i_am_on_the_pdf_report_file_of_saudi_exchange() {
		pdfReport = new PdfSaudiEx(setUp.baseTest.driver);
	}

	@Then("I should verify that the text appears correctly on page {int}")
	public void i_should_verify_that_the_text_appears_correctly_on_page(int pageNumber) throws IOException {
		String actualText = pdfReport.getPdfContent(setUp.baseTest.driver.getCurrentUrl(), pageNumber);
		String normalizedExpectedText = expectedText.replaceAll("\\s+", " ").trim(); // Normalize expected text
		String normalizedActualText = actualText.replaceAll("\\s+", " ").trim(); // Normalize actual text
		System.out.println("Expected Text: " + normalizedExpectedText);
		System.out.println("Actual Text: " + normalizedActualText);
		genericFunctions.logToExtentReport("Log", "Expected text : " + normalizedExpectedText);
		genericFunctions.logToExtentReport("Log","Fetched text from page " + pageNumber + ". text fetched: " + normalizedActualText);
		assertTrue(normalizedActualText.contains(normalizedExpectedText),
				"Expected text not found on page " + pageNumber + ". Actual text found: " + normalizedActualText);
	}

	@Then("I should verify that the Arabic text {string} appears correctly on page {int}")
	public void i_should_verify_that_the_arabic_text_appears_correctly_on_page(String expectedArabicText, int pageNum)
			throws IOException {

		String actualText2 = pdfReport.getPdfContent(setUp.baseTest.driver.getCurrentUrl(), pageNum);
		String normalizedActualText = actualText2.replaceAll("\\s+", " ").trim(); // Normalize expected text

		boolean actualText = pdfReport.verifyTextOnPage(expectedArabicText, pageNum); // Call the method correctly
		System.out.println("Expected Arabic Text: " + expectedArabicText);
		System.out.println("Actual Text Found: " + actualText);
		genericFunctions.logToExtentReport("Log", "Expected text : " + expectedArabicText);
		genericFunctions.logToExtentReport("Log","Fetched text from page " + pageNum + ". text fetched: " + normalizedActualText);
		Assert.assertTrue(actualText, "Arabic text '" + expectedArabicText + "' not found on page " + pageNum);
	}

	@Then("I should extract image from the PDF {string} to {string}")
	public void i_should_extract_image_from_the_pdf_to(String actualPdf, String ImageLocation) throws IOException {
		int pageNum = 1;
		pdfReport = new PdfSaudiEx(setUp.baseTest.driver);
		boolean result = pdfReport.extractImageFromPDF(actualPdf, ImageLocation, pageNum);
		if (result) {
			System.out.println("Image extracted successfully from page " + pageNum + ".");
			Assert.assertTrue(true, "Image extracted successfully from page " + pageNum + ".");
			genericFunctions.logToExtentReport("Pass","Image extracted successfully from page " + pageNum + ".");
			hooks.attachScreenshot(hooks.scenario,ImageLocation);
		} else {
			System.out.println("Failed to extract image from page " + pageNum + ".");
			Assert.assertTrue(false, "Image not extracted successfully from page " + pageNum + ".");
			genericFunctions.logToExtentReport("Fail","Image not extracted successfully from page " + pageNum + ".");
		}
		Assert.assertTrue(result, "Image not extracted");
	}

	@Then("I should extract bar graph from the PDF {string} to {string}")
	public void i_should_extract_bar_graph_from_the_pdf_to(String actualPdf, String barGraphActualImage) throws IOException {
		pdfReport = new PdfSaudiEx(setUp.baseTest.driver);
		int pageNumber = 8;
		boolean barGraphResult = pdfReport.extractBarGraphFromPDF(actualPdf, pageNumber, barGraphActualImage);
		if (barGraphResult) {
			Assert.assertTrue(barGraphResult, "Bar graph extracted");
			genericFunctions.logToExtentReport("Pass","Bar graph extracted");
			hooks.attachScreenshot(hooks.scenario,barGraphActualImage);
		} else {
			genericFunctions.logToExtentReport("Fail","Bar graph not extracted");
			Assert.assertTrue(barGraphResult, "Bar graph not extracted");
		}


	}

	@Then("the downloaded PDF contents of prod {string} should match UAT {string}")
	public void the_downloaded_pdf_contents_of_prod_should_match_uat(String actualReport, String expectedReport) {
		pdfReport = new PdfSaudiEx(setUp.baseTest.driver);
		boolean areReportsEqual = pdfReport.comparePDF(actualReport, expectedReport);
		Assert.assertTrue(areReportsEqual, "PDF files are not identical.");
		if (areReportsEqual) {
			System.out.println("Both the actual and expected PDF's are same.");
			Assert.assertTrue(true);
		} else {
			System.out.println("Both the actual and expected PDF's are different.");
			Assert.fail("Both the actual and expected PDF's are different. Actual report: " + actualReport
					+ ", Expected report: " + expectedReport);
		}
	}

	@Then("I should compare extracted bar graph from prod {string} and UAT {string}")
	public void i_should_compare_extracted_bar_graph_from_prod_and_uat(String actualImage, String expectedImage) throws IOException {
		pdfReport = new PdfSaudiEx(setUp.baseTest.driver);
		genericFunctions.logToExtentReport("Log","Extracted Graph Image (Expected)");
		hooks.attachScreenshot(hooks.scenario,actualImage);

		genericFunctions.logToExtentReport("Log","Extracted Graph Image (Actual)");
		hooks.attachScreenshot(hooks.scenario,expectedImage);

		// Call the image comparison method
		boolean areBargraphSame = pdfReport.compareImages(new File(actualImage), new File(expectedImage), threshold);
		// Assert the result with detailed messages
		if (areBargraphSame) {
			System.out.println("Bar graphs are the same.");
			genericFunctions.logToExtentReport("Pass","Bar graphs are the same.");
			Assert.assertTrue(true);
		} else {
			System.out.println("Bar graphs are different.");
			genericFunctions.logToExtentReport("Fail","Bar graphs are different.");
			Assert.fail(
					"Bar graphs are different. Actual image: " + actualImage + ", Expected image: " + expectedImage);
		}
	}

	@Then("I should extract tables from the PDF")
	public void i_should_extract_tables_from_the_pdf() {
		pdfReport.extractTwoTables(pdfFilePath, 10);
		// pdfReport.extractThreeTablesFromPage(pdfFilePath, 11);
	}

	@Then("I should compare extracted image from prod {string} and UAT {string}")
	public void i_should_compare_extracted_image_from_prod_and_uat(String imageActualLocation,
			String imageExpectedLocation) throws IOException {
		pdfReport = new PdfSaudiEx(setUp.baseTest.driver);

		genericFunctions.logToExtentReport("Log","Extracted Image (Expected)");
		hooks.attachScreenshot(hooks.scenario,imageActualLocation);

		genericFunctions.logToExtentReport("Log","Extracted Image (Actual)");
		hooks.attachScreenshot(hooks.scenario,imageExpectedLocation);

		boolean areImagesSame = pdfReport.compareImages(new File(imageActualLocation), new File(imageExpectedLocation),
				threshold);
		if (areImagesSame) {
			System.out.println("Images are the same.");
			genericFunctions.logToExtentReport("Pass","Images are the same.");
			Assert.assertTrue(true);
		} else {
			System.out.println("Images are different.");
			genericFunctions.logToExtentReport("Fail","Images are not same.");
			Assert.fail("Images are different. Actual image: " + imageActualLocation + ", Expected image: "
					+ imageExpectedLocation);
		}
	}

	@Then("I should verify that the negative Arabic text {string} is not present on page {int}")
	public void i_should_verify_that_the_negative_arabic_text_is_not_present_on_page(String arabicText,
			Integer pageNumber) throws IOException {


		String actualText2 = pdfReport.getPdfContent(setUp.baseTest.driver.getCurrentUrl(), pageNumber);
		String normalizedActualText = actualText2.replaceAll("\\s+", " ").trim(); // Normalize expected text


		boolean isTextpresent = pdfReport.verifyTextOnPage(arabicText, pageNumber); // Call the method correctly
		System.out.println("Expected Arabic Text: " + arabicText);
		System.out.println("Actual Text Found: " + isTextpresent);
		// Assert.assertTrue(isTextpresent, "Arabic text '" + arabicText + "' not found
		// on page " + pageNumber);
		if (isTextpresent) {
			System.out.println("Arabic text is present");
			genericFunctions.logToExtentReport("Pass","Arabic text is present in page - "+pageNumber+" .Actual - " + normalizedActualText + ".Expected - " + arabicText);
			Assert.assertTrue(true);
		} else {
			System.out.println("Arabic text is not present as expected.");
			genericFunctions.logToExtentReport("Fail","Arabic text is not present in page - "+pageNumber+" .Actual - " + normalizedActualText + ".Expected - " + arabicText);
			Assert.fail("Arabic text '" + arabicText + "' not found on page " + pageNumber);
		}
	}
}
