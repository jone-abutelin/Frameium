package com.frameium.utilities.pdfutils;
import org.apache.pdfbox.pdmodel.PDDocument;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import java.awt.geom.Rectangle2D;

public class ReadTextHeaderFooter {
	/**
	 * Extracts text from a specified region of a PDF file.
	 *
	 * @param filePath The path to the PDF file.
	 * @param region   The rectangular region from which to extract text.
	 * @return The text content extracted from the specified region.
	 * 
	 */
	public static String extractTextFromRegion(String filePath, Rectangle2D region) throws IOException {
		File file = new File(filePath);
		PDDocument document = PDDocument.load(file);
		PDFTextStripperByArea textStripper = new PDFTextStripperByArea();
		textStripper.addRegion("region", region);
		textStripper.extractRegions(document.getPage(0)); // Assuming the region is on the first page
		String extractedText = textStripper.getTextForRegion("region");
		document.close();
		return extractedText;
	}
}



