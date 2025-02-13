package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReaderUtil {

    private static Properties properties;

    // Constructor to load the properties file
    public PropertyFileReaderUtil() {
        properties = new Properties();
        try (InputStream inputStream = (getClass().getClassLoader().getResourceAsStream("testdata.properties"))) {
            if (inputStream != null) {
                properties.load(inputStream);  // Load the properties from the file
            } else {
                System.out.println("Property file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading property file");
        }
    }

    // Method to get the value of a property by key
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getWaitInSeconds() {
        return Integer.parseInt(properties.getProperty("explicitWait"));
    }
}
