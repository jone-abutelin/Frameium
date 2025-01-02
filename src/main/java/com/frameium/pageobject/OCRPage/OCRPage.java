package com.frameium.pageobject.OCRPage;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import  javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;




public class OCRPage
{
    private Tesseract tesseract;
    private String extractedText;
    private String opencvImagePath;

    public OCRPage(String language) {
        // Load the OpenCV native library
     // System.setProperty("java.library.path", "src/test/resources/OCR_Data/openCv");
       // System.loadLibrary("opencv_java4100");
       String libPath = "src/test/resources/OCR_Data/openCv";
       System.setProperty("java.library.path", libPath + File.pathSeparator + System.getProperty("java.library.path"));

        try {
            System.loadLibrary("opencv_java4100");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("OpenCV library not found: " + e.getMessage());
        }

        /*// Set up Tesseract
        tesseract = new Tesseract();
        tesseract.setDatapath("src/test/resources/OCR_Data/Tess4J/eng.traineddata/");
        tesseract.setLanguage("eng");
*/
        // Initialize the tesseract object
        initTesseract(language);
    }
    private synchronized void initTesseract(String language) {
        // Set the TESSDATA_PREFIX environment variable
        System.setProperty("TESSDATA_PREFIX", "src/test/resources/OCR_Data/Tess4J/");

        // Set up Tesseract
        tesseract = new Tesseract();
        tesseract.setDatapath("src/test/resources/OCR_Data/Tess4J/");
        tesseract.setLanguage(language);
    }

    public void loadImage(String fileName) {
        // Load the image
        File imageFile = new File("src/test/resources/OCR_Data/" + fileName);

        // Convert the image to grayscale using OpenCV
        Mat img = Imgcodecs.imread(imageFile.getAbsolutePath());
        Mat grayImage = new Mat();
        Imgproc.cvtColor(img, grayImage, Imgproc.COLOR_BGR2GRAY);

// Determine the threshold value based on the image name
        int thresholdValue = getThresholdValue(fileName);

        // Apply the determined threshold
        Imgproc.threshold(grayImage, grayImage, thresholdValue, 255, Imgproc.THRESH_BINARY);

// Manually set a threshold value
      //  Imgproc.threshold(grayImage, grayImage, 215, 255, Imgproc.THRESH_BINARY);


        // Optionally apply resizing to scale the image (increase by 2x)
        Mat resizedImage = new Mat();
        Imgproc.resize(grayImage, resizedImage, new Size(), 4, 4, Imgproc.INTER_CUBIC);  // Resize with fx=2, fy=2


        // Display the processed grayscale image
        HighGui.imshow("Preprocessed Image",grayImage);  // Show image in a window
        HighGui.resizeWindow("Preprocessed Image", 800, 600);
        HighGui.waitKey(1000);  // Wait for user input to close the window
        HighGui.destroyAllWindows();  // Close the display window



        // Save the processed grayscale image
        opencvImagePath = "src/test/resources/OCR_Data/processed_" + fileName;
        Imgcodecs.imwrite(opencvImagePath,  resizedImage);
        // Add logging statement to check if image is loaded correctly
        System.out.println("Image loaded: " + fileName);
        System.out.println("Threshold value: " + thresholdValue);
    }
    private int getThresholdValue(String fileName) {
        // Set different threshold values for different images
        if (fileName.equals("image10.jpg")) {
            return 180; // Threshold for image.jpg
        } else if (fileName.equals("images11.jpg")) {
            return 230; // Threshold for images5.jpg
        } else if (fileName.equals("DataTable.jpg")) {
            return 170; // Threshold for images11.jpg
        } else if (fileName.equals("bookPage2.jpg")) {
            return 123; // Threshold for bookPage2.jpg
        } else if (fileName.equals("MultiLanguageImage.jpg")) {
            return 134; // Threshold for bookPage2.jpg
        }
        else if (fileName.equals("GIF_Image.gif")) {
            return 110; // Threshold for bookPage2.jpg
        }
        // Default threshold
        return 215; // Fallback threshold
    }
    public void performOCR() {
        try {
            // Load the processed image
            File processedImageFile = new File(opencvImagePath);
            Mat image = Imgcodecs.imread(processedImageFile.getAbsolutePath());

            if (image.empty()) {
                System.err.println("Error reading image file: Image is empty");
                return; // Exit if the image is empty
            }

            // Convert the image to grayscale
            Mat grayImage = new Mat();
            Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

            // Apply adaptive thresholding
            Imgproc.adaptiveThreshold(grayImage, grayImage, 255,
                    Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 11, 2);

            // Create a BufferedImage from the Mat
            BufferedImage bufferedImage = new BufferedImage(grayImage.cols(), grayImage.rows(), BufferedImage.TYPE_BYTE_GRAY);
            byte[] data = ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
            grayImage.get(0, 0, data);

            // Perform OCR
            extractedText = tesseract.doOCR(bufferedImage);
            System.out.println("Extracted Text: " + extractedText);

        } catch (TesseractException e) {
            System.err.println("Error during text extraction: " + e.getMessage());
        }
    }
    public String getExtractedText() {
        // Add logging statement to check if extracted text is being returned correctly
        //System.out.println("Returning extracted text: " + extractedText);
        return extractedText;
    }
    public void loadGifImage(String fileName) {
        // Get file extension to decide how to load the image
        String fileExtension = getFileExtension(fileName);
        Mat matImage = null;

        // Check if the image is a GIF
        if (fileExtension.equalsIgnoreCase("gif")) {
            // Use ImageIO to load GIF
            File imageFile = new File("src/test/resources/OCR_Data/" + fileName);
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(imageFile);  // Handles GIF loading
            } catch (IOException e) {
                System.err.println("Error loading GIF image: " + e.getMessage());
                return;
            }

            // Check if the image is successfully loaded
            if (bufferedImage == null) {
                System.err.println("Error: BufferedImage is null. GIF may not be loaded correctly.");
                return;
            }

            // Convert BufferedImage to Mat
            matImage = bufferedImageToMat(bufferedImage);
        } else {
            // Use OpenCV to load JPG/PNG
            matImage = Imgcodecs.imread("src/test/resources/OCR_Data/" + fileName);
            if (matImage.empty()) {
                System.err.println("Error: Could not load image using OpenCV. File may be missing or unsupported.");
                return;
            }
        }

        // Convert the Mat image to grayscale
        Mat grayImage = new Mat();
        Imgproc.cvtColor(matImage, grayImage, Imgproc.COLOR_BGR2GRAY);

        // Apply thresholding or other OpenCV operations
        int thresholdValue = getThresholdValue(fileName);
        Imgproc.threshold(grayImage, grayImage, thresholdValue, 255, Imgproc.THRESH_BINARY);

        // Optionally apply resizing (if needed)
        Mat resizedImage = new Mat();
        Imgproc.resize(grayImage, resizedImage, new Size(), 4, 4, Imgproc.INTER_CUBIC);

        // Save the processed image using ImageIO
        opencvImagePath = "src/test/resources/OCR_Data/processed_" + fileName; // Ensure this is set
        File outputFile = new File(opencvImagePath);
        try {
            ImageIO.write(matToBufferedImage(resizedImage), "png", outputFile);
        } catch (IOException e) {
            System.err.println("Error writing image: " + e.getMessage());
        }

        System.out.println("Image loaded and processed: " + fileName);
    }


    // Add this method to convert Mat to BufferedImage
    public BufferedImage matToBufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = mat.channels() * mat.cols() * mat.rows();
        byte[] buffer = new byte[bufferSize];
        mat.get(0, 0, buffer);
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        return image;
    }

    public void setLanguage(String language) {
        if (tesseract != null) {
            tesseract.setLanguage(language); // Change the language dynamically
        } else {
            System.err.println("Tesseract has not been initialized.");
        }
    }
    // Add this method inside your OCRPage class
    public String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf('.');
        if (lastIndexOf == -1) {
            return "";  // In case there's no extension
        }
        return fileName.substring(lastIndexOf + 1);
    }
    // Add this method inside your OCRPage class
    public Mat bufferedImageToMat(BufferedImage image) {
        byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, pixels);
        return mat;
    }

}
