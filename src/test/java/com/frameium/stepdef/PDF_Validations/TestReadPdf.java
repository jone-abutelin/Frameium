package com.frameium.stepdef.PDF_Validations;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameium.utilities.pdfutils.*;

public class TestReadPdf {
	// Replace with the path to the desired PDF file
	String filePath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/Free_Test_Data_100KB_PDF.pdf";
	int pageNumber = 1; // Replace with the desired page number
	ReadPdf pdfReader = new ReadPdf();
	/*
	 * Test to verify reading text from a specific page of a PDF.
	 */
	@Test
	public void testReadTextFromSpecificPage() {
		//Replace with expected text of desired PDF file
		String expectedText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "; 
		String actualText = pdfReader.readTextFromPDFPage(filePath, pageNumber);
		System.out.println("Text from page " + pageNumber + ":\n" + actualText);
		boolean isTextPresent = actualText.contains(expectedText);
		Assert.assertTrue(isTextPresent, "Expected text is not found on the specific page");
	}
	/*
	 * Test to verify reading text from all pages of a PDF.
	 */
	@Test
	public void testReadTextFromAllPages() {
		String text = pdfReader.readTextFromAllPages(filePath);
		System.out.println("Text from all pages:\n" + text);
		Assert.assertNotNull(text, "Extracted text from all pages is null");
	}
}
