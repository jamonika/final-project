package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Path;

public class ChromeDriverWrapper extends DriverWrapper {
    public static final Path chromedriverPath = Path.of("src", "main", "resources", "drivers", "chromedriver.exe");

    @Override
    public WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", chromedriverPath.toString());
        return new ChromeDriver();
    }
}
