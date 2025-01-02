package com.frameium.utilities.pdfutils;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDNonTerminalField;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfToExcelConverter {
    public static String sheetName = "PDFFormData";
	/**
	 * Converts PDF form data to an Excel file.
	 *
	 * @param pdfFileName The path to the input PDF file.
	 * @throws Exception If an error occurs during the conversion process.
	 */
	public static boolean PdfFormdataToExcel(String pdfFileName, String outputExcelPath) throws Exception {
		HSSFWorkbook myWorkBook = new HSSFWorkbook();
		HSSFSheet mySheet = myWorkBook.createSheet(sheetName);
		HSSFRow myRow = null;
		PDDocument document = null;
		FileOutputStream out = null;
		int rowCount = 0;

		try {
			String excelFileName = sheetName + ".xls";
			// Construct the full path for the output Excel file
			File outputFile = new File(outputExcelPath + "/" + excelFileName);

			// Ensure the parent directory exists
			if (!outputFile.getParentFile().exists()) {
				outputFile.getParentFile().mkdirs();
			}

			// Load the PDF document
			document = PDDocument.load(new File(pdfFileName));
			PDDocumentCatalog pdCatalog = document.getDocumentCatalog();
			PDAcroForm pdAcroForm = pdCatalog.getAcroForm();
			List<PDField> fields = pdAcroForm.getFields(); // Get all fields in the form

			// Create headers in the Excel sheet
			myRow = mySheet.createRow(0);
			myRow.createCell(0).setCellValue("FieldName");
			myRow.createCell(1).setCellValue("FieldValue");

			// Process each form field and populate the Excel sheet
			for (PDField field : fields) {
				HSSFRow newRow = mySheet.createRow(++rowCount);
				processField(field, field.getPartialName(), newRow);
			}

			// Write the workbook to the specified output path
			out = new FileOutputStream(outputFile);
			myWorkBook.write(out);

			System.out.println("Excel file successfully created at: " + outputFile.getAbsolutePath());
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (document != null) {
					document.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/*
	 * Recursively processes a PDF form field and populates the Excel row.
	 *
	 * @param field   The PDF form field.
	 * @param sParent The parent field name (for hierarchical fields).
	 * @param row     The Excel row to populate.
	 * 
	 */
	private static void processField(PDField field, String sParent, HSSFRow row) throws IOException {
		String partialName = field.getPartialName();
		@SuppressWarnings("unused")
		String fieldValue = null;
		if (field instanceof PDNonTerminalField) {
			if (!sParent.equals(field.getPartialName())) {
				if (partialName != null) {
					sParent = sParent + "." + partialName;
				}
			}
			for (PDField child : ((PDNonTerminalField) field).getChildren()) {
				processField(child, sParent, row);
			}
		} else {
			fieldValue = field.getValueAsString();
			row.createCell(0).setCellValue(field.getFullyQualifiedName());
			row.createCell(1).setCellValue(field.getValueAsString());
		}
	}
}
