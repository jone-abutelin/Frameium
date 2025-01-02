package com.frameium.pageobject.SaudiExchange;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.frameium.genericfunctions.GenericFunctions;

public class MarketReportSaudiEx extends GenericFunctions {

	private String selectMarketTable = "body > div > div > div.contentArea > div:nth-child(3) > div.market_summary_Table > div > table";

	private By downloadClick = By.cssSelector(
			"#layoutContainers > div.wptheme1Col > div.component-container.wpthemeFull.wpthemeRow.id-Z7_A7LONGKGPU58MTT7UEV9OCDG06 > div > section > section > div.marketReportsRgt > div:nth-child(2) > div > ul > li:nth-child(1) > div.lngDownload > a:nth-child(2)");

	private Document doc;
	private static String downloadPath = "C:/Users/RijuJohn/Downloads/";

	public MarketReportSaudiEx(WebDriver driver) {
		driver = this.driver;

	}

	public File downloadDetailedDailyReport() throws InterruptedException {
		clickElementtobeInteractable(downloadClick);
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

	public void unzipDownloadedReport(String zipFilePath, String unzippedFilePath, String unzippedFileName) {
		unzipFile(zipFilePath, unzippedFilePath, unzippedFileName);
	}

	public boolean unzipFile(String zipFilePath, String unzippedFilePath, String unzippedFileName) {
		try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath))) {
			ZipEntry entry = zipIn.getNextEntry();
			// Iterate over each entry in the zip file
			while (entry != null) {
				String filePath = unzippedFilePath + File.separator + unzippedFileName;
				if (!entry.isDirectory()) {
					// If entry is a file, extract it
					extractFile(zipIn, filePath);
				} else {
					// If entry is a directory, create it
					File dir = new File(filePath);
					dir.mkdir();
				}
				zipIn.closeEntry();
				entry = zipIn.getNextEntry();
			}
			System.out.println("Files unzipped successfully.");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
		try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
			byte[] bytesIn = new byte[4096];
			int read;
			while ((read = zipIn.read(bytesIn)) != -1) {
				bos.write(bytesIn, 0, read);
			}
		}
	}

	public boolean compareHTML(String prodHtml, String uatHtml) {
		try {

			// Read the content of the HTML files
			String actualHtml = readFileContent(prodHtml);
			String expectedHtml = readFileContent(uatHtml);
			if (actualHtml == null || expectedHtml == null) {
	            System.out.println("One or both HTML files not found.");
	            return false;
	        }
			// Logging to debug the content of the HTML files
			System.out.println("Production HTML content:");
			System.out.println(prodHtml);
			System.out.println("UAT HTML content:");
			System.out.println(uatHtml);

			// Parse the HTML strings into Jsoup Documents
			Document prodDoc = Jsoup.parse(actualHtml);
			Document uatDoc = Jsoup.parse(expectedHtml);

			// Normalize the documents to ensure consistent formatting
			prodDoc.normalise();
			uatDoc.normalise();

			// Compare the HTML structure
			boolean areEqual = prodDoc.outerHtml().equals(uatDoc.outerHtml());

			// Log the result of the comparison
			if (areEqual) {
				System.out.println("HTML contents match.");
			} else {
				System.out.println("HTML contents do not match.");
			}

			return areEqual;
		} catch (Exception e) {
			System.err.println("Error comparing HTML files: " + e.getMessage());
			e.printStackTrace();
			return false;
		}

	}

	private String readFileContent(String filePath) throws IOException {
		StringBuilder contentBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				contentBuilder.append(line);
			}
		}
		return contentBuilder.toString();
	}

	public List<String[]> getMarketSummaryValues() {
		parseHTMLContent();
		return getTheValues();
	}

	private void parseHTMLContent() {
		// Get the HTML content of the current page
		String htmlContent = driver.getPageSource();
		// Parse the HTML content using Jsoup
		doc = Jsoup.parse(htmlContent);
	}

	private List<String[]> getTheValues() {
		List<String[]> keyValuePairs = new ArrayList<>();

		// Find the table containing the market summary
		Element marketSummaryTable = doc.selectFirst(selectMarketTable);
		// System.out.println("Market Summary Table: " + marketSummaryTable);

		// Check if marketSummaryTable is not null
		if (marketSummaryTable != null) {
			// Find all rows in the table
			int rowCount = 0;
			for (Element row : marketSummaryTable.select("tr")) {
				// Get the columns of each row
				Element keyColumn = row.selectFirst("td:first-child");
				Element valueColumn = row.selectFirst("td:last-child");
				// Check if keyColumn is not null
				if (keyColumn != null && valueColumn != null) {
					// Extract the key and value
					String key = keyColumn.text();
					String value = valueColumn.text();
					// Print out the key-value pair
					System.out.println(key + ": " + value);
					keyValuePairs.add(new String[] { key, value });
					rowCount++;
				}
				if (rowCount == 2) {
					break;
				}
			}
		} else {
			System.out.println("Market Summary Table is null");
		}

		return keyValuePairs;
	}

}
