package com.frameium.stepdef.PDF_Validations;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameium.utilities.pdfutils.*;

import java.io.IOException;
import java.util.Map;

public class TestPdfExcelCompare {

	@Test
	public void testFormFieldNameValueComparison() throws IOException {
		// Specify the file paths
		String pdfPath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/pdf-conversion-services.pdf";
		String excelPath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/Book1.xlsx";

		// Extract form field data from PDF and Excel
		Map<String, String> pdfFormFieldData = PdfExcelCompare.extractFormFieldNameValueFromPDF(pdfPath);
		Map<String, String> excelFormFieldData = PdfExcelCompare.readFormFieldNameValueFromExcel(excelPath);

		// Compare field names and values
		for (Map.Entry<String, String> entry : pdfFormFieldData.entrySet()) {
			String fieldName = entry.getKey();
			String pdfFieldValue = entry.getValue();

			// Check if the field name is present in Excel
			if (excelFormFieldData.containsKey(fieldName)) {
				String excelFieldValue = excelFormFieldData.get(fieldName);

				// Compare the field values
				Assert.assertEquals(pdfFieldValue, excelFieldValue,
						"Field value for field '" + fieldName + "' does not match. Actual: " + pdfFieldValue + " Expected: " + excelFieldValue);
			} else {
				Assert.fail("Field name '" + fieldName + "' from PDF is not present in Excel.");
			}
		}
		System.out.println("PDF Form Field Data:\n" + pdfFormFieldData);
		System.out.println("Excel Form Field Data:\n" + excelFormFieldData);
	}
}
