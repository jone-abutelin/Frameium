package com.frameium.utilities.pdfutils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;
import com.google.zxing.multi.MultipleBarcodeReader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;



/**
 * A utility class for decoding QR codes from a PDF file.
 */
public class QrCodeDecoder {
	 /**
     * Decode QR codes from a PDF file.
     *
     * @param qrCodeFile The path to the PDF file containing QR codes.
     * @return A list of decoded text from the QR codes in the PDF.
     */
    public static List<String> pdfBarcodeQRdecoder(String qrCodeFile) {
        List<String> decodedTexts = new ArrayList<>();
        try {
            File file = new File(qrCodeFile);
            try (PDDocument document = PDDocument.load(file)) {
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                int numPages = document.getNumberOfPages();
                for (int pageIndex = 0; pageIndex < numPages; pageIndex++) {
                    BufferedImage image = pdfRenderer.renderImage(pageIndex);
                    LuminanceSource source = new BufferedImageLuminanceSource(image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                    com.google.zxing.Reader reader = new MultiFormatReader();
                    Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
                    hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
                    // GenericMultipleBarcodeReader to handle multiple barcodes on the same page
                    MultipleBarcodeReader multiReader = new GenericMultipleBarcodeReader(reader);
                    Result[] results = multiReader.decodeMultiple(bitmap, hints);
                    for (Result result : results) {
                        decodedTexts.add(result.getText());
                    }
                }
            }
            System.out.println("Decoded QR codes: " + decodedTexts);
            return decodedTexts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decodedTexts;
    }
}
