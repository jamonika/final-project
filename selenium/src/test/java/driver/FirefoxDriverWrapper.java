package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.nio.file.Path;

public class FirefoxDriverWrapper extends DriverWrapper {
    private static final Path firefoxDriverPath = Path.of("src", "main", "resources", "drivers", "geckodriver.exe");


    @Override
    public WebDriver createDriver() {
        System.setProperty("webdriver.gecko.driver", firefoxDriverPath.toString());
        return new FirefoxDriver();
    }
}
