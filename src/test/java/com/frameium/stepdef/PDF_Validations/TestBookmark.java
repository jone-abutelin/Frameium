package com.frameium.stepdef.PDF_Validations;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameium.utilities.pdfutils.*;

import java.util.List;

public class TestBookmark {
	String filePath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/pdf-example-bookmarks.pdf";
	/*
	 * Test to verify the Bookmarks from the PDF
	 */

	@Test
	public void testExtractPDFBookmarks() {
		List<String> extractedBookmarks = BookmarkPdf.extractPDFBookmarks(filePath);
		// Replace these with expected bookmark titles in your PDF
		String expectedBookmark1 = "Introduction";
		String expectedBookmark2 = "Getting Started";
		Assert.assertTrue(extractedBookmarks.contains(expectedBookmark1), "Expected Bookmark 1 not found");
		Assert.assertTrue(extractedBookmarks.contains(expectedBookmark2), "Expected Bookmark 2 not found");
		System.out.println("Test Passed - Expected Bookmarks found in the extracted bookmarks!");
	}
}
