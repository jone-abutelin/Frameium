package com.frameium.utilities.pdfutils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PdfExcelCompare {
	/*
	 * Extracts form field names and values from a PDF file.
	 *
	 * @param pdfPath The path to the PDF file.
	 * @return A map containing form field names and their corresponding values.
	 * 
	 */
	public static Map<String, String> extractFormFieldNameValueFromPDF(String pdfPath) throws IOException {
		PDDocument document = PDDocument.load(new File(pdfPath));
		Map<String, String> pdfFormFieldData = new HashMap<>();

		// Access the form
		PDAcroForm acroForm = document.getDocumentCatalog().getAcroForm();

		// Iterate over form fields and extract data
		for (PDField field : acroForm.getFieldTree()) {
			pdfFormFieldData.put(field.getFullyQualifiedName(), field.getValueAsString());
		}

		document.close();
		return pdfFormFieldData;
	}
	/*
	 * Reads form field names and values from an Excel file.
	 *
	 * @param excelPath The path to the Excel file.
	 * @return A map containing form field names and their corresponding values.
	 * 
	 */
	public static Map<String, String> readFormFieldNameValueFromExcel(String excelPath) throws IOException {
		Workbook workbook = WorkbookFactory.create(new File(excelPath));
		Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
		Map<String, String> excelFormFieldData = new HashMap<>();

		// Iterate over rows to find form field data
		for (Row row : sheet) {
			Cell fieldNameCell = row.getCell(0); // Assuming the form field name is in the first column
			Cell fieldValueCell = row.getCell(1); // Assuming the form field value is in the second column

			if (fieldNameCell != null && fieldValueCell != null) {
				excelFormFieldData.put(fieldNameCell.toString(), fieldValueCell.toString());
			}
		}

		workbook.close();
		return excelFormFieldData;
	}
}
