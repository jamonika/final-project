package configuration;

import driver.DriverWrapper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BasicTest {

    protected WebDriver webDriver;

    @BeforeTest
    public void beforeTest() {
        webDriver = DriverWrapper.getDriverType().createDriver();
        webDriver.manage().window().maximize();
    }

    @AfterTest
    public void afterTest() {
        webDriver.close();
        webDriver.quit();
    }
}
