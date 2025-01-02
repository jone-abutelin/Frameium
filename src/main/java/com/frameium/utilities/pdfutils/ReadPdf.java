package com.frameium.utilities.pdfutils;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ReadPdf {
	/*
	 * Reads text from a specific page of a PDF file.
	 * 
	 * @param filePath   The path to the PDF file.
	 * @param pageNumber The page number from which to read the text.
	 * @return The text content from the specified page.
	 */
	public String readTextFromPDFPage(String filePath, int pageNumber) {
		String textFromPage = "";
		PDDocument document = null;
		try {
			File file = new File(filePath);
			document = PDDocument.load(file);
			if (pageNumber >= 1 && pageNumber <= document.getNumberOfPages()) {
				PDFTextStripper pdfStripper = new PDFTextStripper();
				pdfStripper.setStartPage(pageNumber);
				pdfStripper.setEndPage(pageNumber);
				textFromPage = pdfStripper.getText(document);
			} else {
				System.err.println("Invalid page number");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return textFromPage;
	}
	/*
	 * Reads text from all pages of a PDF file.
	 * 
	 * @param filePath The path to the PDF file.
	 * @return The concatenated text content from all pages.
	 */
	public String readTextFromAllPages(String filePath) {
		String textFromAllPages = "";
		PDDocument document = null;
		try {
			File file = new File(filePath);
			document = PDDocument.load(file);

			PDFTextStripper pdfStripper = new PDFTextStripper();
			textFromAllPages = pdfStripper.getText(document);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return textFromAllPages;
	}
}
