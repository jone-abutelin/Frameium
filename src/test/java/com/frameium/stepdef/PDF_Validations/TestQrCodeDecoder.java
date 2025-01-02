package com.frameium.stepdef.PDF_Validations;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameium.utilities.pdfutils.*;

public class TestQrCodeDecoder {
	// Replace with the path to the desired PDF file
	String filePath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/op.pdf";

	/**
	 * Test to verify the extracted text from QRCode after decoding 
	 */
	@Test
	public void testPdfBarcodeQRDecoder() {
		// Call the pdfBarcodeQRdecoder method and pass the file path
		List<String> decodedTexts = QrCodeDecoder.pdfBarcodeQRdecoder(filePath);

		// Now you can use TestNG assertions to verify the expected results
		Assert.assertNotNull(decodedTexts);
		Assert.assertFalse(decodedTexts.isEmpty());
	}
}
