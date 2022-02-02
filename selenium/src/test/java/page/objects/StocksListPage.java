package page.objects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StocksListPage extends Page{

    @FindBy(css = "#boxQuotes table tbody")
    private WebElement quotesTable;

    public StocksListPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getUrl() {
        return null;
    }

    public List<WebElement> getQuotesRowsFromPage() {
        waitForElement(quotesTable);
        return quotesTable.findElements(By.tagName("tr")).stream()
                .filter(w -> !w.getAttribute("class").contains("adv")).collect(Collectors.toList());
    }
}
