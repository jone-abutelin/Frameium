package com.frameium.stepdef.PDF_Validations;

import org.testng.annotations.Test;

import com.frameium.utilities.pdfutils.*;

import org.testng.Assert;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class TestHeaderFooterReader {
    //Give desired filepath
	private static final String filePath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/Doc2.pdf";
	// Test to verify the extraction of text from specified header and footer regions in a PDF.

	@Test
	public void testExtractTextFromRegion() throws IOException {
		//Coordrinates can be adjusted based on PDF size
		Rectangle2D headerRegion = new Rectangle2D.Float(40, 10, 560, 50); 
		Rectangle2D footerRegion = new Rectangle2D.Float(40, 750, 560, 500);
		String extractedHeaderText = ReadTextHeaderFooter.extractTextFromRegion(filePath, headerRegion);
		Assert.assertNotNull(extractedHeaderText, "Header text not extracted");
		System.out.println("Extracted Header: \n" + extractedHeaderText);
		String extractedFooterText = ReadTextHeaderFooter.extractTextFromRegion(filePath, footerRegion);
		Assert.assertNotNull(extractedFooterText, "Footer text not extracted");
		System.out.println("Extracted Footer: \n" + extractedFooterText);
	}
}



