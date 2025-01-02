package com.frameium.utilities.pdfutils;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;

public class PageCountPdf {
	 /*
     * Get the number of pages in a PDF document.
     *
     * @param filePath The path to the PDF file.
     * @return The number of pages in the PDF document.
     */
	public static int getPageCount(String filePath) throws IOException {
		int pageCount = 0;
		try{
			PDDocument document = PDDocument.load(new File(filePath));
			pageCount = document.getNumberOfPages();
			document.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}	
		return pageCount;
	}
}



