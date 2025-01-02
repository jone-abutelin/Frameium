package com.frameium.utilities.pdfutils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadHyperlinkBookmark {
	/*
	 * Extracts hyperlinks from the provided PDF file.
	 * @param filePath Path to the PDF file.
	 * @return A list of extracted hyperlinks.
	 */
	public static List<String> extractPDFHyperlinks(String filePath) {
		List<String> extractedLinks = new ArrayList<>();
		try (PDDocument document = PDDocument.load(new File(filePath))) {
			for (PDPage page : document.getPages()) {
				for (PDAnnotation annotation : page.getAnnotations()) {
					if (annotation instanceof PDAnnotationLink) {
						PDAnnotationLink link = (PDAnnotationLink) annotation;
						if (link.getAction() instanceof PDActionURI) {
							String hyperlink = ((PDActionURI) link.getAction()).getURI();
							extractedLinks.add(hyperlink);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return extractedLinks;
	}
}
