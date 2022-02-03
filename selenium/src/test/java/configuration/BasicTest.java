package configuration;

import driver.DriverWrapper;
import driver.FirefoxDriverWrapper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BasicTest {

    protected WebDriver webDriver;
    private DriverWrapper driverType = DriverWrapper.getDriverType();

    @BeforeTest
    public void beforeTest() {
        webDriver = driverType.createDriver();
        webDriver.manage().window().maximize();
    }

    @AfterTest
    public void afterTest() {
        webDriver.close();
        if (!driverType.getClass().equals(FirefoxDriverWrapper.class)){
            webDriver.quit();
        }
    }
}
