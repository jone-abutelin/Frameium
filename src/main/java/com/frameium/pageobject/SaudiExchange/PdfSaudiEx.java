package com.frameium.pageobject.SaudiExchange;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.testng.Assert;

import javax.imageio.ImageIO;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.frameium.genericfunctions.GenericFunctions;

public class PdfSaudiEx extends GenericFunctions {
	private By annualReportsList = By.cssSelector(
			"#layoutContainers > div.wptheme1Col > div.component-container.wpthemeFull.wpthemeRow.id-Z7_5A602H80OOA970QFJBGILA3MS3 > div > section > section > div.tradingList > ul");
	private By yearDropdown = By.id("selEventYear");
	private By monthDropdown = By.id("selEventMnth");
	private By reportDropdown = By.id("selRptType");
	private By downloadClick = By.cssSelector("#\\32 024_02_SA-MonthlyReports > div.lngDownload > a:nth-child(2)");

	public PdfSaudiEx(WebDriver driver) {
		driver = this.driver;
	}

	// Return the list of annual reports
	public List<WebElement> getAnnualReportsList() {
		List<WebElement> annualReports = driver.findElements(annualReportsList);
		for (WebElement report : annualReports) {
			System.out.println(report.getText());
		}
		return annualReports;
	}

	// Check if the list of annual reports is not empty
	public boolean isAnnualReportsListNotEmpty() {
		List<WebElement> annualReports = getAnnualReportsList();
		return annualReports.size() > 0;
	}

	public void selectAnnualReport(String year, String month, String reportType) {
		WebElement yearDropdownElement = driver.findElement(yearDropdown);
		Select yearSelect = new Select(yearDropdownElement);
		yearSelect.selectByVisibleText(year);

		WebElement monthDropdownElement = driver.findElement(monthDropdown);
		Select monthSelect = new Select(monthDropdownElement);
		monthSelect.selectByVisibleText(month);

		WebElement reportDropdownElement = driver.findElement(reportDropdown);
		Select reportSelect = new Select(reportDropdownElement);
		reportSelect.selectByVisibleText(reportType);

		logToExtentReport("Log","Selected report for - year - " + year + ", month - " + month + "report type - " + reportType);
	}

	public File downloadAnnualReport(String downloadPath) throws InterruptedException {
		clickElement(downloadClick);
		Thread.sleep(6000);
		return getLatestDownloadedFile(downloadPath);
	}

	public File getLatestDownloadedFile(String downloadPath) {
		File dir = new File(downloadPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			throw new RuntimeException("No files found in the directory: " + downloadPath);
		}

		// Find the latest file based on last modified time
		File latestFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (files[i].lastModified() > latestFile.lastModified()) {
				latestFile = files[i];
			}
		}
		return latestFile;
	}

	//	public File openReport(String downloadPath) {
	//
	//		File directory = new File(downloadPath);
	//		File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));
	//
	//		if (files != null && files.length > 0) {
	//			Arrays.sort(files, Comparator.comparingLong(File::lastModified).reversed());
	//			File latestPdf = files[0]; // Get the latest PDF file
	//			try {
	//				Desktop.getDesktop().open(latestPdf); // Open the latest PDF file
	//				return latestPdf;
	//			} catch (IOException e) {
	//				e.printStackTrace();
	//			}
	//		} else {
	//			System.out.println("No PDF files found in the specified directory.");
	//		}
	//		return null;
	//	}

	public boolean verifyTextOnPage(String expectedText, int pageNumber) {
		try {
			String pdfContents = getPdfContent(driver.getCurrentUrl(), pageNumber);
			return pdfContents.contains(expectedText);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getPdfContent(String currentUrl, int pageNumber) throws IOException {
		URL pdfURL = new URL(currentUrl);
		try (InputStream is = pdfURL.openStream()) {
			PDDocument doc = PDDocument.load(is);
			PDFTextStripper stripper = new PDFTextStripper();
			stripper.setStartPage(pageNumber);
			stripper.setEndPage(pageNumber);
			return stripper.getText(doc);
		}
	}

	public void extractImagesFromPDF(String filePath) {
		try (PDDocument document = PDDocument.load(new File(filePath))) {
			int pageNumber = 1;
			for (PDPage page : document.getPages()) {
				// Iterate through resources on the page and get the object
				for (COSName cosName : page.getResources().getXObjectNames()) {
					PDXObject xobject = page.getResources().getXObject(cosName);
					if (xobject instanceof PDImageXObject) {
						// Create a file for the extracted image and save it in desired location
						File file = new File("C:/Users/JoneAbutelin/Downloads/All Image/image" + pageNumber + ".png");
						BufferedImage image = ((PDImageXObject) xobject).getImage(); // Get the image and write it to
						// the file
						ImageIO.write(image, "png", file);
					}
				}
				pageNumber++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void extractImagesFromSpecificPage(String filePath, int targetPage) {
		try (PDDocument document = PDDocument.load(new File(filePath))) {
			PDPage page = document.getPage(targetPage - 1);
			int pageNumber = targetPage;
			// Iterate through resources on the page and get the object
			for (COSName cosName : page.getResources().getXObjectNames()) {
				PDXObject xobject = page.getResources().getXObject(cosName);
				if (xobject instanceof PDImageXObject) {
					// Create a file for the extracted image and save it in the desired location
					File file = new File(
							"C:/Users/JoneAbutelin/Downloads/Specific Page Image/image" + pageNumber + ".png");
					BufferedImage image = ((PDImageXObject) xobject).getImage(); // Get the image and write it to the
					// file
					ImageIO.write(image, "png", file);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void extractBarGraph(String pdfFilePath, int pageNumber) {
		try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
			PDFRenderer pdfRenderer = new PDFRenderer(document);
			BufferedImage image = pdfRenderer.renderImageWithDPI(pageNumber - 1, 300);
			File outputFile = new File("C:/Users/JoneAbutelin/Downloads/Bar Graph/barGraph1.png");
			ImageIO.write(image, "png", outputFile);
			System.out.println("Bar graph extracted successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void extractTwoTables(String pdfFilePath, int pageNumber) {
		try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
			if (pageNumber >= 1 && pageNumber <= document.getNumberOfPages()) {
				PDFRenderer pdfRenderer = new PDFRenderer(document);
				BufferedImage pageImage = pdfRenderer.renderImageWithDPI(pageNumber - 1, 300);
				// Identify and extract tables from the page image
				BufferedImage[] tables = extractTablesFromImage(pageImage);
				// Save each table as a separate image
				for (int i = 0; i < tables.length; i++) {
					saveImage(tables[i],
							"C:/Users/JoneAbutelin/Downloads/Tables/page_" + pageNumber + "_table_" + (i + 1) + ".png");
				}
				System.out.println("Tables extracted successfully from page " + pageNumber + ".");
			} else {
				System.out.println("Invalid page number. Please provide a valid page number.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage[] extractTablesFromImage(BufferedImage image) {
		// Assuming there are two tables and they are positioned at fixed locations on
		// the page
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();
		int table1X = 50; // X-coordinate of the top-left corner of the first table
		int table1Y = 100; // Y-coordinate of the top-left corner of the first table
		int table1Width = imageWidth - 100; // Width of the first table
		int table1Height = imageHeight / 2 - 50; // Height of the first table
		int table2X = 50; // X-coordinate of the top-left corner of the second table
		int table2Y = imageHeight / 2 + 50; // Y-coordinate of the top-left corner of the second table
		int table2Width = imageWidth - 100; // Width of the second table
		int table2Height = imageHeight / 2 - 50; // Height of the second table

		// Extract each table from the image
		BufferedImage table1 = image.getSubimage(table1X, table1Y, table1Width, table1Height);
		BufferedImage table2 = image.getSubimage(table2X, table2Y, table2Width, table2Height);

		return new BufferedImage[] { table1, table2 };
	}

	public static void saveImage(BufferedImage image, String outputPath) {
		try {
			ImageIO.write(image, "png", new File(outputPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void extractThreeTablesFromPage(String pdfFilePath, int pageNumber) {
		try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
			if (pageNumber >= 1 && pageNumber <= document.getNumberOfPages()) {
				PDFRenderer pdfRenderer = new PDFRenderer(document);
				BufferedImage pageImage = pdfRenderer.renderImageWithDPI(pageNumber - 1, 300);
				// Identify and extract tables from the page image
				BufferedImage[] tables = extractThreeTablesFromImage(pageImage);
				// Save each table as a separate image
				for (int i = 0; i < tables.length; i++) {
					saveImage(tables[i],
							"C:/Users/JoneAbutelin/Downloads/Tables/page_" + pageNumber + "_table_" + (i + 1) + ".png");
				}
				System.out.println("Tables extracted successfully from page " + pageNumber + ".");
			} else {
				System.out.println("Invalid page number. Please provide a valid page number.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage[] extractThreeTablesFromImage(BufferedImage image) {
		// Assuming there are three tables and they are positioned at fixed locations on
		// the page
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();
		int table1X = 50; // X-coordinate of the top-left corner of the first table
		int table1Y = 100; // Y-coordinate of the top-left corner of the first table
		int table1Width = imageWidth - 100; // Width of the first table
		int table1Height = imageHeight / 3 - 50; // Height of the first table
		int table2X = 50; // X-coordinate of the top-left corner of the second table
		int table2Y = imageHeight / 3 + 50; // Y-coordinate of the top-left corner of the second table
		int table2Width = imageWidth - 100; // Width of the second table
		int table2Height = imageHeight / 3 - 50; // Height of the second table
		int table3X = 50; // X-coordinate of the top-left corner of the third table
		int table3Y = 2 * imageHeight / 3 + 50; // Y-coordinate of the top-left corner of the third table
		int table3Width = imageWidth - 100; // Width of the third table
		int table3Height = imageHeight / 3 - 50; // Height of the third table

		// Extract each table from the image
		BufferedImage table1 = image.getSubimage(table1X, table1Y, table1Width, table1Height);
		BufferedImage table2 = image.getSubimage(table2X, table2Y, table2Width, table2Height);
		BufferedImage table3 = image.getSubimage(table3X, table3Y, table3Width, table3Height);

		return new BufferedImage[] { table1, table2, table3 };
	}

	public File downloadReportProd(String actualPath) throws InterruptedException {
		clickElement(downloadClick);
		Thread.sleep(6000);
		return getLatestDownloadedFile(actualPath);
	}

	public File downloadReportUAT(String expectedPath) throws InterruptedException {
		clickElement(downloadClick);
		Thread.sleep(6000);
		return getLatestDownloadedFile(expectedPath);
	}
	
	 public void extractAllImagesFromPDF(String filePath, String allImagesLocation) {
		 try (PDDocument document = PDDocument.load(new File(filePath))) {
		        int pageNumber = 1;
		        for (PDPage page : document.getPages()) {
		            // Iterate through resources on the page and get the object
		            for (COSName cosName : page.getResources().getXObjectNames()) {
		                PDXObject xobject = page.getResources().getXObject(cosName);
		                if (xobject instanceof PDImageXObject) {
		                    // Create a file for the extracted image and save it in the desired location
		                    File file = new File(allImagesLocation + "/image" + pageNumber + ".png");
		                    BufferedImage image = ((PDImageXObject) xobject).getImage(); // Get the image
		                    ImageIO.write(image, "png", file); 
		                }
		            }
		            pageNumber++;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
}

	public boolean extractBarGraphFromPDF(String actualPdf, int pageNumber, String barGraphActualImage) {
		try (PDDocument document = PDDocument.load(new File(actualPdf))) {
	        if (pageNumber >= 1 && pageNumber <= document.getNumberOfPages()) {
	            PDFRenderer pdfRenderer = new PDFRenderer(document);
	            BufferedImage image = pdfRenderer.renderImageWithDPI(pageNumber - 1, 300);
	            File outputFile = new File(barGraphActualImage);
	            ImageIO.write(image, "png", outputFile);
	            System.out.println("Bar graph extracted successfully.");
				Assert.assertTrue(true, "Bar graph extracted successfully.");
				logToExtentReport("Pass","Bar graph extracted successfully.");

	            return true;
	        } else {
	            System.out.println("Invalid page number. Please provide a valid page number.");
				Assert.assertTrue(false, "Bar graph not extracted successfully.");
				logToExtentReport("Fail","Bar graph not extracted successfully.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;	
	}
	
	

	public boolean comparePDF(String actualReport, String expectedReport) {
			try {
				String expectedText = extractTextFromPDF(expectedReport);
				String actualText = extractTextFromPDF(actualReport);
				return expectedText.equals(actualText);
			} catch (IOException e) {
				System.err.println("Error comparing PDF files: " + e.getMessage());
				return false;
			}
		
	}
	private static String extractTextFromPDF(String filePath) throws IOException {
		PDDocument document = PDDocument.load(new File(filePath));
		try {
			PDFTextStripper textStripper = new PDFTextStripper();
			return textStripper.getText(document);
		} finally {
			document.close();
		}
	}

    public boolean compareImages(File fileA, File fileB, double threshold) {
        BufferedImage imgA = null;
        BufferedImage imgB = null;

        try {
            imgA = ImageIO.read(fileA);
            imgB = ImageIO.read(fileB);
        } catch (IOException e) {
            System.out.println(e);
        }

        int width1 = imgA.getWidth();
        int width2 = imgB.getWidth();
        int height1 = imgA.getHeight();
        int height2 = imgB.getHeight();

        // Resize the images to match dimensions if necessary
        if (width1 != width2 || height1 != height2) {
            imgA = resizeImage(imgA, width2, height2);
            width1 = width2;
            height1 = height2;
        }
            long difference = 0;

            for (int y = 0; y < height1; y++) {
                for (int x = 0; x < width1; x++) {
                    int rgbA = imgA.getRGB(x, y);
                    int rgbB = imgB.getRGB(x, y);

                    int redA = (rgbA >> 16) & 0xff;
                    int greenA = (rgbA >> 8) & 0xff;
                    int blueA = (rgbA) & 0xff;

                    int redB = (rgbB >> 16) & 0xff;
                    int greenB = (rgbB >> 8) & 0xff;
                    int blueB = (rgbB) & 0xff;

                    difference += Math.abs(redA - redB);
                    difference += Math.abs(greenA - greenB);
                    difference += Math.abs(blueA - blueB);
                }
            }

            double totalPixels = width1 * height1 * 3;
            double avgDifferentPixels = difference / totalPixels;
            double percentage = (avgDifferentPixels / 255) * 100;

            System.out.println("Difference Percentage: " + percentage);

            // Check if the difference percentage is below the threshold
            return percentage < threshold;
        
    }
    private BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g.dispose();
        return resizedImage;
    }

    public File copyFile(String sourceFolderPath, String destinationFolderPath, String customFileName) {
        try {
            File sourceFolder = new File(sourceFolderPath);
            File destinationFolder = new File(destinationFolderPath);

            // Validate that the source folder exists and is a directory
            if (!sourceFolder.exists() || !sourceFolder.isDirectory()) {
                throw new IllegalArgumentException("Source folder does not exist or is not a directory.");
            }

            // Create the destination folder if it doesn't exist
            if (!destinationFolder.exists()) {
                destinationFolder.mkdirs();
            }

            // Get the list of files in the source folder
            File[] files = sourceFolder.listFiles();

            // Check if there are any files in the source folder
            if (files == null || files.length == 0) {
                throw new IllegalArgumentException("No files found in the source folder.");
            }

            // Find the latest file in the source folder based on last modified timestamp
            File latestFile = Files.walk(sourceFolder.toPath())
                                   .filter(Files::isRegularFile)
                                   .map(Path::toFile)
                                   .max(Comparator.comparing(File::lastModified))
                                   .orElse(null);

            if (latestFile == null) {
                throw new IllegalArgumentException("No files found in the source folder.");
            }

            // Create a destination file path with the custom file name
            File destinationFile = new File(destinationFolder, customFileName);

            // Copy the latest file to the destination folder
            Files.copy(latestFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Latest file copied successfully to: " + destinationFile.getAbsolutePath());
            
            return destinationFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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
		                    File file = new File(imageLocation);
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
