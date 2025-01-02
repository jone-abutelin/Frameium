package com.frameium.utilities.pdfutils;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class ExtractImage {
	/*
	 * Extract images from a PDF document and save them as PNG files.
	 *
	 * @param filePath The path to the PDF file.
	 */
	public void extractImagesFromPDF(String filePath) {
		try(PDDocument document = PDDocument.load(new File(filePath))){
			int pageNumber =1;
			for(PDPage page:document.getPages()) {
				// Iterate through resources on the page and get the object
				for(COSName cosName:page.getResources().getXObjectNames()) {
					PDXObject xobject = page.getResources().getXObject(cosName);
					if (xobject instanceof PDImageXObject) {
						// Create a file for the extracted image and save it in desired location
						File file = new File("C:/Users/JoneAbutelin/Downloads/image" + pageNumber + ".png");
						BufferedImage image = ((PDImageXObject) xobject).getImage();  // Get the image and write it to the file
						ImageIO.write(image, "png", file);
					}				}
				pageNumber++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean extractImageFromPDF(String actualPdf, String imageLocation, int pageNum) {
		try (PDDocument document = PDDocument.load(new File(actualPdf))) {
			if (pageNum >= 1 && pageNum <= document.getNumberOfPages()) {
				PDPage page = document.getPage(pageNum - 1); // Adjusting to 0-based index

				// Iterate through resources on the specified page and get the object
				for (COSName cosName : page.getResources().getXObjectNames()) {
					PDXObject xobject = page.getResources().getXObject(cosName);
					if (xobject instanceof PDImageXObject) {
						// Create a file for the extracted image and save it in desired location
						File file = new File(imageLocation + "/image" + pageNum + ".png");
						BufferedImage image = ((PDImageXObject) xobject).getImage(); // Get the image and write it to the file
						return ImageIO.write(image, "png", file);
					}
				}
			} else {
				System.out.println("Invalid page number. Please provide a valid page number.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false; // Return false if extraction fails or page number is invalid
	}

}
