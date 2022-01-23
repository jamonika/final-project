package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirefoxDriverWrapper extends DriverWrapper {
    @Override
    public WebDriver createDriver() {
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
        return new ChromeDriver();
    }
}
