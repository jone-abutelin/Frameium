package com.frameium.utilities.pdfutils;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDVariableText;

public class InteractivePdf {
	/*
	 * Fills the form fields of a PDF document.
	 *
	 * @param filePath        Path to the input PDF file.
	 * @param outputFilePath  Path to the output PDF file after filling the form.
	 */
	public static void fillPDF(String filePath) {
		try {
			File file = new File(filePath);
			PDDocument document = PDDocument.load(file);
			// Disable JavaScript execution
			//	document.getDocumentCatalog().setAcroForm(null);
			PDDocumentCatalog catalog = document.getDocumentCatalog();
			PDAcroForm form = catalog.getAcroForm();
			if (form != null) {
				for (PDField field : form.getFields()) {
					System.out.println("Field Name: " + field.getFullyQualifiedName());
					System.out.println("Field Type: " + field.getFieldType());
					// Set font size for variable text fields
					if (field instanceof PDVariableText) {
						PDVariableText variableText = (PDVariableText) field;
						variableText.setDefaultAppearance("/Helv 10 Tf 0 g");
					}
					// Switch statement to set values based on field names
					switch (field.getFullyQualifiedName()) {
					case "Customer Name":
						field.setValue("John Doe");
						break;
					case "Customer Address2":
						field.setValue("123 Main Street");
						break;
					case "Customer Mobile":
						field.setValue("9654321982");
						break;
					case "Customer Address1":
						field.setValue("4/4216/C Rajiv nagar");
						break;
					case "Customer email":
						field.setValue("jiju@gmail.com");
						break;
					case "Returning Customer":
						field.setValue("Yes");
						break;
					case "Customer Website":
						field.setValue("jiju.com");
						break;
					case "Pin Code":
						field.setValue("629001");
						break;
					case "Amount Paid Part 1":
						field.setValue("1000");
						break;
					case "Amount Paid Part 2":
						field.setValue("2000");
						break;
					case "Amount Paid Total":
						field.setValue("3000");
						break;
					case "Enquiry For":
						field.setValue("Amount Withdrawal");
						break;
					case "Reference":
						field.setValue("Smith Raj");
						break;
					case "Bank Branch":
						field.setValue("Nagercoil");
						break;
					case "Account Type":
						field.setValue("Savings");
						break;
					case "Account Number":
						field.setValue("1043001000345");
						break;
					case "Customer Bank Name":
						field.setValue("State Bank");
						break;
					case "Customer Date of Birth":
						field.setValue("10-09-2001");
						break;
					case "Fordm Date":
						field.setValue("14-11-2023");
						break;
					default :
						
					
					}
				}
				document.save(filePath);
				document.close();
				System.out.println("PDF Form filled successfully.");
			} else {
				System.out.println("The PDF does not contain any form fields.");
			}
		} catch (IOException e) {
			e.printStackTrace(); // Print any exceptions for debugging
		}
	}   
}
