package com.frameium.utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class ConfigurationManager {
    private Properties properties;

    // Constructor to load properties from a given file path
    public ConfigurationManager(String filePath) {
        properties = new Properties();
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file: " + filePath, e);
        }
    }

    // Method to get property value by key
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    // Static method to create ConfigurationManager for a specific properties file
    public static ConfigurationManager createConfigurationManager(String filePath) {
        return new ConfigurationManager(filePath);
    }
}
