package configuration;

import lombok.Getter;

import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.fail;

@Getter
public class PropertiesHelper {

    private String browser;

    private static PropertiesHelper propertiesHelper;

    public static PropertiesHelper getInstance() {
        if (propertiesHelper == null) {
            propertiesHelper = new PropertiesHelper();
        }
        return propertiesHelper;
    }

    private PropertiesHelper() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("test.properties"));
            browser = properties.getProperty("browser.type");
        } catch (IOException e) {
            fail("Test properties file incorrect");
        }
    }
}
