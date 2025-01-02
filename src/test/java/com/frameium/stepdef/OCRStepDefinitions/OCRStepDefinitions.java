package com.frameium.stepdef.OCRStepDefinitions;
import net.sourceforge.tess4j.Tesseract;
import io.cucumber.java.en.*;
import java.io.File;
import static org.testng.AssertJUnit.assertFalse;
import com.frameium.pageobject.OCRPage.OCRPage;

public class OCRStepDefinitions {

    private OCRPage ocrPage; //

    Tesseract tesseract = new Tesseract();

    @Given("the OCR page is loaded with image {string} and language {string}")
    public void theOCRPageIsLoadedWithImageAndLanguage(String image, String language) {
        ocrPage = new OCRPage(language);
        ocrPage.loadImage(image);
    }

    /*@Given("I have an image file {string}")
    public void iHaveAnImageFile(String fileName) {

        ocrPage.loadImage(fileName);

    }*/

    @When("I perform OCR on the image")
    public void iPerformOCROnTheImage() {
//        ocrPage.performOCR();
        if (ocrPage != null) {
            ocrPage.performOCR(); // Call performOCR only if ocrPage is not null
        } else {
            throw new IllegalStateException("OCRPage is not initialized.");
        }
    }

    @Then("the extracted text should not be empty")
    public void theExtractedTextShouldNotBeEmpty()
    {
        String extractedText = ocrPage.getExtractedText();
        assertFalse("Extracted text should not be empty", extractedText.isEmpty());
    }


    @Given("the OCR page is loaded with gif image {string} and language {string}")
    public void theOCRPageIsLoadedWithGifImageAndLanguage(String image, String language) {
        ocrPage = new OCRPage(language);
        ocrPage.loadGifImage(image);
    }
}
