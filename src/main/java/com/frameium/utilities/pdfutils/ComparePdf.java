package com.frameium.utilities.pdfutils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.IOException;

public class ComparePdf {
	/*
     * Compares the text content of two PDF files.
     *
     * @param expectedFilePath The path to the expected PDF file.
     * @param actualFilePath   The path to the actual PDF file.
     * @return true if the PDF files have identical text content, false otherwise.
     */
	public static boolean comparePDF(String expectedFilePath, String actualFilePath) {
		try {
			String expectedText = extractTextFromPDF(expectedFilePath);
			String actualText = extractTextFromPDF(actualFilePath);
			return expectedText.equals(actualText);
		} catch (IOException e) {
			System.err.println("Error comparing PDF files: " + e.getMessage());
			return false;
		}
	}
	/*
     * Extracts text content from a PDF file.
     * @param filePath The path to the PDF file.
     * @return The extracted text content.
     */
	private static String extractTextFromPDF(String filePath) throws IOException {
		PDDocument document = PDDocument.load(new File(filePath));
		try {
			PDFTextStripper textStripper = new PDFTextStripper();
			return textStripper.getText(document);
		} finally {
			document.close();
		}
	}
}
