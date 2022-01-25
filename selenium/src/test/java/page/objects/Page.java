package page.objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public abstract class Page {

    protected WebDriver webDriver;
    public static final int DEFAULT_TIMEOUT = 5;

    public Page(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void openPage() {
        webDriver.navigate().to(getUrl());
    }

    public abstract String getUrl();

    protected boolean checkIfElementIsDisplayed(WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NotFoundException | StaleElementReferenceException | ElementNotVisibleException e) {
            return false;
        }
    }

    protected void waitForElement(WebElement webElement) {
        waitForElement(webElement, DEFAULT_TIMEOUT);
    }

    protected void waitForElement(WebElement webElement, int timeout) {
        await().atMost(timeout, TimeUnit.SECONDS).until(() -> checkIfElementIsDisplayed(webElement));
    }
}
