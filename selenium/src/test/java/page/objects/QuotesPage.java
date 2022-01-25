package page.objects;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class QuotesPage extends Page{

    @FindBy(css = "#boxQuotes table tbody")
    private WebElement quotesTable;

    public QuotesPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getUrl() {
        return null;
    }

}
