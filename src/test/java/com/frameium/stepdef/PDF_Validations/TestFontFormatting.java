package com.frameium.stepdef.PDF_Validations;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameium.utilities.pdfutils.*;

import java.util.Set;
import java.util.HashSet;

public class TestFontFormatting {
	// Update this with desired PDF file path
	String filePath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/Free_Test_Data_100KB_PDF.pdf";
	/**
	 * Test to verify font formatting analysis from a PDF document.
	 */
	@Test
	public void testFontFormattingFromPdf() {
		Set<String> expectedFontNames = new HashSet<>();
		expectedFontNames.add("BCDEEE+Open Sans"); // Add the expected font names here
		expectedFontNames.add("BCDFEE+Calibri");
		Set<String> fontNames = FontFormat.textAnalysis(filePath);
		// Print the extracted font names
		for (String font : fontNames) {
			System.out.println("Extracted Font Name: " + font);
		}
		// Assertion to check if font names match the expected values
		Assert.assertEquals(fontNames, expectedFontNames, "Fonts extracted match the expected fonts");
	}
}
