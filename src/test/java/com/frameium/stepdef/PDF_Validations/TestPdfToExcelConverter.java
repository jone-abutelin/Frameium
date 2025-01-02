package com.frameium.stepdef.PDF_Validations;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameium.utilities.pdfutils.*;

import java.io.File;

public class TestPdfToExcelConverter {
	/*
	 * Test the PdfFormdataToExcel method of PdfToExcelConverter.
	 */
	@Test
	public void testPdfFormdataToExcel() {
		String pdfFilePath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/pdfform.pdf";
		String expectedExcelFilePath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/Downloads";

		try {
			PdfToExcelConverter.PdfFormdataToExcel(pdfFilePath, expectedExcelFilePath);

			File expectedExcelFile = new File(expectedExcelFilePath);
			Assert.assertTrue(expectedExcelFile.exists(), "Excel file does not exist");
			System.out.println("Success: Excel file generated at " + expectedExcelFilePath);

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Exception occurred during PDF to Excel conversion");
		}
	}
}

