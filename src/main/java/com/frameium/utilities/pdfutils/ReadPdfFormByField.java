package com.frameium.utilities.pdfutils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class ReadPdfFormByField {

	public static void main(String[] args) throws IOException {
		// Specify the path to the desired PDF file
		String filePath = "C:/Users/JoneAbutelin/Documents/pdfform.pdf";
		Scanner scanner = new Scanner(System.in);
		String fieldName;

		System.out.print("Enter the field name: ");
		fieldName = scanner.nextLine();

		// Retrieve and display the field value
		String fieldValue = getFieldValue(fieldName, filePath);
		if (fieldValue.equals("Field not found or empty")) {
			System.out.println("Invalid field name.");
		} else {
			System.out.println("Value of " + fieldName + ": " + fieldValue);
		}

		HashMap<String, String> fieldMap = listAllFields(filePath);
		System.out.println("List of all fields:");
		for (String name : fieldMap.keySet()) {
			String value = fieldMap.get(name);
			System.out.println("Field Name: " + name + ", Value: " + value);
		}

		scanner.close();
	}

	/*
	 * To get specific field value
	 */
	public static String getFieldValue(String fieldName, String filePath) {
		try {
			File file = new File(filePath);
			PDDocument document = PDDocument.load(file);
			String value = getFieldValue(fieldName, document);
			document.close();
			return value;
		} catch (IOException e) {
			e.printStackTrace();
			return "Error loading the document";
		}
	}

	/*
	 * To get all the field value using HashMap
	 */
	public static HashMap<String, String> listAllFields(String filePath) {
		try {
			File file = new File(filePath);
			PDDocument document = PDDocument.load(file);
			HashMap<String, String> fieldMap = listAllFields(document);
			document.close();
			return fieldMap;
		} catch (IOException e) {
			e.printStackTrace();
			return new HashMap<>();
		}
	}

	private static String getFieldValue(String fieldName, PDDocument document) {
		PDDocumentCatalog catalog = document.getDocumentCatalog();
		PDField field = catalog.getAcroForm().getField(fieldName);
		return (field != null) ? field.getValueAsString() : "Field not found or empty";
	}

	private static HashMap<String, String> listAllFields(PDDocument document) {
		HashMap<String, String> fieldMap = new HashMap<>();
		PDDocumentCatalog catalog = document.getDocumentCatalog();
		PDAcroForm acroForm = catalog.getAcroForm();
		List<PDField> fields = acroForm.getFields();

		if (fields != null && !fields.isEmpty()) {
			for (PDField field : fields) {
				String name = field.getFullyQualifiedName();
				String value = field.getValueAsString();
				fieldMap.put(name, value);
			}
		} else {
			System.out.println("No fields found in the form.");
		}
		return fieldMap;
	}
}
