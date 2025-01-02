package com.frameium.stepdef.PDF_Validations;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameium.utilities.pdfutils.*;

import java.util.List;

public class TestHyperlinkBookmark {
	String filePath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/pdf_hyperlink_example.pdf"; // Replace with the actual file path of the PDF
	/*
	 * Test to verify the extraction of hyperlinks from a PDF.
	 */
	@Test
	public void testExtractPDFHyperlinks() {
		List<String> extractedLinks = ReadHyperlinkBookmark.extractPDFHyperlinks(filePath);
		if (extractedLinks.isEmpty()) {
			Assert.fail("No hyperlinks found in the PDF.");
		} else {
			// Add your expected URLs for validation
			String expectedURL1 = "https://www.gpo.gov/fdsys/granule/CFR-2004-title45-vol2/CFR-2004-title45-vol2-part301";
			String expectedURL2 = "https://www.gpo.gov/fdsys/granule/CFR-2010-title45-vol2/CFR-2010-title45-vol2-part302";
			Assert.assertTrue(extractedLinks.contains(expectedURL1), "Expected URL 1 not found");
			Assert.assertTrue(extractedLinks.contains(expectedURL2), "Expected URL 2 not found");
			System.out.println("Test Passed - Expected URLs found in the extracted hyperlinks!");
		}
	}
}


