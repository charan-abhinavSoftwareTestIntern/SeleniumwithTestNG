package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReaderUtil {

    private static final Properties properties = new Properties();

    // Static block to ensure properties are loaded only once
    static {
        try (InputStream inputStream = PropertyFileReaderUtil.class.getClassLoader().getResourceAsStream("testdata.properties")) {
            if (inputStream != null) {
                properties.load(inputStream);
                System.out.println("✅ Properties file loaded successfully!");
            } else {
                System.err.println("❌ testdata.properties file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Error loading property file.");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getWaitInSeconds() {
        return Integer.parseInt(properties.getProperty("explicitWait", "10")); // Default to 10 seconds
    }
}
