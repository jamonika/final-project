package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivacyAndPolicyPage extends Page{

    @FindBy(css = "#cmp-iframe")
    private WebElement privacyAndPolicyIframe;

    @FindBy(css = "[data-button-type~=acceptAll]")
    private WebElement acceptButton;

    public PrivacyAndPolicyPage(WebDriver webDriver) {
        super(webDriver);
        webDriver.switchTo().frame(privacyAndPolicyIframe);
    }

    @Override
    public String getUrl() {
        return null;
    }

    public void acceptPrivacyAndPolicy() {
        waitForElement(acceptButton);
        acceptButton.click();
        webDriver.switchTo().defaultContent();
    }
}
