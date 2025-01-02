package com.frameium.utilities.pdfutils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

public class BookmarkPdf {
	/**
	 * Extracts bookmarks from a PDF file.
	 *
	 * @author filePath The path to the PDF file.
	 * @param filePath The path to the PDF file.
	 * @return none
	 */
	public static List<String> extractPDFBookmarks(String filepath) {
		List<String> extractedBookmarks = new ArrayList<>();
		try (PDDocument document = PDDocument.load(new File(filepath))) {
			PDDocumentOutline outline = document.getDocumentCatalog().getDocumentOutline();
			if (outline != null) {
				collectBookmarks(outline.getFirstChild(), extractedBookmarks);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return extractedBookmarks;
	}
	/*
	 * Collects bookmarks from the outline.
	 *
	 * @param bookmark   The current bookmark.
	 * @param bookmarks  The list to store extracted bookmarks.
	 */
	private static void collectBookmarks(PDOutlineItem bookmark, List<String> bookmarks) {
		PDOutlineItem current = bookmark;
		while (current != null) {
			bookmarks.add(current.getTitle());
			if (current.getFirstChild() != null) {
				collectBookmarks(current.getFirstChild(), bookmarks);
			}
			current = current.getNextSibling();
		}
	}
}
