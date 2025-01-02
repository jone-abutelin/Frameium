package com.frameium.stepdef.PDF_Validations;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameium.utilities.pdfutils.*;

public class TestExtractedImage {
    /*
     * Test to verify image extraction from a PDF document.
     */
//	@Test
//	public void testExtractedImageFromPdf() {
//		// Give desired pdf file path
//		String filePath = "C:/Users/JoneAbutelin/Downloads/Free_Test_Data_100KB_PDF.pdf";
//		String expectedImageName = "image2.png";
//		ExtractImage imageExtractor = new ExtractImage();
//		imageExtractor.extractImagesFromPDF(filePath);
//		String extractedImagesPath = "C:/Users/JoneAbutelin/Downloads/";
//		boolean isImagePresent = isSpecificImagePresent(extractedImagesPath, expectedImageName);
//		Assert.assertTrue(isImagePresent, "Expected image should be present");
//		if (isImagePresent) {
//			System.out.println("The expected image is present in the directory.");
//		} else {
//			System.out.println("The expected image is not present in the directory.");
//		}
//	}

    /**
     * Method to check if a specific image file is present in the given directory.
     *
     * @param extractedImagesPath The path of the directory to search for the image
     *                            file.
     * @param expectedImageName   The name of the expected image file to find.
     * @return True if the specific image file is found in the directory; false
     * otherwise.
     */

    private boolean isSpecificImagePresent(String extractedImagesPath, String expectedImageName) {
        File folder = new File(extractedImagesPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().equalsIgnoreCase(expectedImageName)) {
                        return true; // Image file with the specific name found
                    }
                }
            }
        }
        return false; // Specific image file not found in the directory
    }

    @Test
    public void testExtractedImage() {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/Free_Test_Data_100KB_PDF.pdf";
        String imageLocation = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/Downloads";
        int pageNum = 2; // Change to the desired page number
        ExtractImage imageExtractor = new ExtractImage();

        boolean extractionSuccessful = imageExtractor.extractImageFromPDF(filePath, imageLocation, pageNum);
        if (extractionSuccessful) {
            System.out.println("Image extracted successfully from page " + pageNum + ".");
        } else {
            System.out.println("Failed to extract image from page " + pageNum + ".");
        }
    }
}
