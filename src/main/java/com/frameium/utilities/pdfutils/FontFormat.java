package com.frameium.utilities.pdfutils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;

public class FontFormat {
	/*
     * Analyze the fonts used in a PDF document.
     *
     * @param filePath The path to the PDF file.
     * @return A set of font names used in the PDF document.
     */
	public static Set<String> textAnalysis(String filePath) {
		Set<String> fontNames = new HashSet<>();
		try ( PDDocument document = PDDocument.load(new File(filePath))){
			for (PDPage page : document.getPages()) {
				PDResources resources = page.getResources();
				if (resources != null) {
					Iterable<COSName> cosNames = resources.getFontNames();
					for (COSName cosName : cosNames) {
						PDFont font = resources.getFont(cosName);
						fontNames.add(font.getName());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fontNames;
	}
}


