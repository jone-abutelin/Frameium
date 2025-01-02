package com.frameium.stepdef.PDF_Validations;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.frameium.utilities.pdfutils.*;

import java.util.HashMap;

public class TestReadPdfFormByField {
    // File path of the PDF to be tested
    private static final String FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/Data/PDF/pdfform.pdf";

    @Test
    public void testGetFieldValue() {
        String fieldName = "Customer Name"; // Replace with the actual field name in your PDF
        String expectedValue = "John Doe"; // Replace with the expected value for the given field
        String fieldValue = ReadPdfFormByField.getFieldValue(fieldName, FILE_PATH);

        try {
            Assert.assertEquals(fieldValue, expectedValue, "Field value does not match the expected value.");
            // Print actual and expected values when the test passes
            System.out.println("Test Passed - Field Value: " + fieldValue + ", Expected Value: " + expectedValue);
        } catch (AssertionError e) {
            // Print actual and expected field values only when the test fails
            System.out.println("Test Failed - Field Value: " + fieldValue + ", Expected Value: " + expectedValue);
            throw e; 
        }
    }
    @Test
    public void testListAllFields() {
        HashMap<String, String> fieldMap = ReadPdfFormByField.listAllFields(FILE_PATH);

        // Add your expected field names and values here
        HashMap<String, String> expectedFieldMap = new HashMap<>();
        expectedFieldMap.put("Customer Name", "John Doe");
        expectedFieldMap.put("Customer Address1", "4/4216/C Rajiv nagar");

        try {
            // Check that all expected fields are present in the actual field map
            for (String expectedFieldName : expectedFieldMap.keySet()) {
                Assert.assertTrue(fieldMap.containsKey(expectedFieldName), "Field not found: " + expectedFieldName);
                String actualValue = fieldMap.get(expectedFieldName);
                String expectedValue = expectedFieldMap.get(expectedFieldName);
                Assert.assertEquals(actualValue, expectedValue, "Field value does not match for: " + expectedFieldName);
            }           
            System.out.println("Test Passed - All fields match the expected values.");

        } catch (AssertionError e) {
            // Print actual and expected field values only when the test fails
            System.out.println("Actual Field Map: " + fieldMap);
            System.out.println("Expected Field Map: " + expectedFieldMap);
            throw e; 
        }
    }
}
