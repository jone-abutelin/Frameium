package com.frameium.stepdef.PDF_Validations;

import org.testng.annotations.Test;

import com.frameium.utilities.pdfutils.*;

public class TestInteractivePdf {
	//Input desired pdf file path
	private static final String INPUT_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/pdf-conversion-services.pdf";

	/*
	 * Test method to fill a PDF and save it.
	 */
	@Test
	public void testFillPDF() {
		// Fill the PDF with test data and save it without specifying an output file path
		InteractivePdf.fillPDF(INPUT_FILE_PATH);
	}
}
