package com.frameium.stepdef.PDF_Validations;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameium.utilities.pdfutils.*;

public class TestMetadataExtractorPdf {
    // Replace with the path to the desired PDF file
    String filePath = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/fw4.pdf";

    /*
     * Test to verify the functionality of metadata extraction from a PDF.
     */
    @Test
    public void testMetadataExtraction() {
        // Define expected metadata values
        String expectedTitle = "Expected Title";
        String expectedAuthor = "Expected Author";
        String expectedSubject = "Expected Subject";
        String expectedKeywords = "Expected Keywords";
        String expectedCreator = "Expected Creator";
        String expectedProducer = "Expected Producer";
        String expectedCreationDate = "Expected Creation Date";
        String expectedModificationDate = "Expected Modification Date";
        String expectedFileSize = "Expected File Size";
        String expectedPageCount = "Expected Page Count";
        String expectedLanguage = "Expected Language";
        String expectedSecuritySettings = "Expected Security Settings";

        // Extract metadata from the PDF
        String[] extractedMetadata = MetadataExtractorPdf.extractMetadata(filePath);

        // Validating expected against actual metadata
        Assert.assertEquals(extractedMetadata[0], expectedTitle, "Title mismatch. Expected: " + expectedTitle + ", Actual: " + extractedMetadata[0]);
        Assert.assertEquals(extractedMetadata[1], expectedAuthor, "Author mismatch. Expected: " + expectedAuthor + ", Actual: " + extractedMetadata[1]);
        Assert.assertEquals(extractedMetadata[2], expectedSubject, "Subject mismatch. Expected: " + expectedSubject + ", Actual: " + extractedMetadata[2]);
        Assert.assertEquals(extractedMetadata[3], expectedKeywords, "Keywords mismatch. Expected: " + expectedKeywords + ", Actual: " + extractedMetadata[3]);
        Assert.assertEquals(extractedMetadata[4], expectedCreator, "Creator mismatch. Expected: " + expectedCreator + ", Actual: " + extractedMetadata[4]);
        Assert.assertEquals(extractedMetadata[5], expectedProducer, "Producer mismatch. Expected: " + expectedProducer + ", Actual: " + extractedMetadata[5]);
        Assert.assertEquals(extractedMetadata[6], expectedCreationDate, "Creation Date mismatch. Expected: " + expectedCreationDate + ", Actual: " + extractedMetadata[6]);
        Assert.assertEquals(extractedMetadata[7], expectedModificationDate, "Modification Date mismatch. Expected: " + expectedModificationDate + ", Actual: " + extractedMetadata[7]);
        Assert.assertEquals(extractedMetadata[8], expectedFileSize, "File Size mismatch. Expected: " + expectedFileSize + ", Actual: " + extractedMetadata[8]);
        Assert.assertEquals(extractedMetadata[9], expectedPageCount, "Page Count mismatch. Expected: " + expectedPageCount + ", Actual: " + extractedMetadata[9]);
        Assert.assertEquals(extractedMetadata[10], expectedLanguage, "Language mismatch. Expected: " + expectedLanguage + ", Actual: " + extractedMetadata[10]);
        Assert.assertEquals(extractedMetadata[11], expectedSecuritySettings, "Security Settings mismatch. Expected: " + expectedSecuritySettings + ", Actual: " + extractedMetadata[11]);
    }
}
