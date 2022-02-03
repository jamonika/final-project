package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverWrapper extends DriverWrapper {
    @Override
    public WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
        return new ChromeDriver();
    }
}
