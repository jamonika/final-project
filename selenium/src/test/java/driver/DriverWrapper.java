package driver;

import configuration.PropertiesHelper;
import org.openqa.selenium.WebDriver;

public abstract class DriverWrapper {

    public abstract WebDriver createDriver();

    public static DriverWrapper getDriverType() {
        PropertiesHelper propertiesHelper = PropertiesHelper.getInstance();
        String browser = propertiesHelper.getBrowser();

        switch (browser) {
            case "chrome":
                return new ChromeDriverWrapper();
            case "firefox":
                return new FirefoxDriverWrapper();
            default:
                throw new RuntimeException("Selected browser not supported");
        }
    }
}
