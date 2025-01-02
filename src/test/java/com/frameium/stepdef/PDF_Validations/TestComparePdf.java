package com.frameium.stepdef.PDF_Validations;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameium.utilities.pdfutils.*;

public class TestComparePdf {
	/*
	 * Test to compare Pdf files
	 */
	@Test
	public void testComparePDFs() {
		// Give desired PDF paths
		String expectedPdfPath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/pdf-conversion-services.pdf";
		String actualPdfPath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/ESG_Disclosure_Guidelines_EN.pdf";
		boolean arePDFsEqual = ComparePdf.comparePDF(expectedPdfPath, actualPdfPath);
		if (arePDFsEqual) {
			System.out.println("PDF files are identical.");
		} else {
			System.out.println("PDF files are not identical!");
		}
		Assert.assertTrue(arePDFsEqual, "PDF files are not identical.");
	}
}
