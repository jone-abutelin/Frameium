package com.frameium.stepdef.PDF_Validations;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameium.utilities.pdfutils.*;

public class TestPageCount {
	// Replace with the path to the desired PDF file
	String filePath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/Free_Test_Data_100KB_PDF.pdf";
	/*
	 * Test to verify the pageCount of the PDF
	 */
	@Test
	public void testPageCount() throws IOException {
		int expectedPageCount = 3; 
		try {
            int actualPageCount = PageCountPdf.getPageCount(filePath);
            System.out.println("Page count of the PDF: " + actualPageCount);
            Assert.assertEquals(actualPageCount, expectedPageCount, "Page count mismatch");
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
}

