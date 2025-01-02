package com.frameium.utilities.pdfutils;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;

public class MetadataExtractorPdf {
	/*
     * Extract metadata from a PDF file.
     *
     * @param filePath The path to the PDF file.
     * @return An array containing various metadata information.
     */
    public static String[] extractMetadata(String filePath) {
        String[] extractedMetadata = new String[12]; // Array to store metadata
        try {
            File file = new File(filePath);
            PDDocument document = PDDocument.load(file);
            PDDocumentInformation info = document.getDocumentInformation();
            PDDocumentCatalog catalog = document.getDocumentCatalog();
            // Extract metadata
            extractedMetadata[0] = info.getTitle();
            extractedMetadata[1] = info.getAuthor();
            extractedMetadata[2] = info.getSubject();
            extractedMetadata[3] = info.getKeywords();
            extractedMetadata[4] = info.getCreator();
            extractedMetadata[5] = info.getProducer();
            extractedMetadata[6] = info.getCreationDate().toString();
            extractedMetadata[7] = info.getModificationDate().toString();
            extractedMetadata[8] = String.valueOf(file.length());
            extractedMetadata[9] = String.valueOf(document.getNumberOfPages());
            extractedMetadata[10] = catalog.getLanguage(); 
            extractedMetadata[11] = catalog.getActions().toString(); // Security Settings
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return extractedMetadata;
    }
}
