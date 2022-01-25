package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuotesPagesBarPage extends Page {

    public static final String BASE_QUOTES_URL = "https://www.bankier.pl/gielda/notowania/akcje";

    @FindBy(css = ".pagination.top a")
    private List<WebElement> quotesBar;

    public QuotesPagesBarPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getUrl() {
        return BASE_QUOTES_URL;
    }

    public List<String> getAllQuotesBarTexts() {
        return quotesBar.stream()
                .filter(w -> !w.getAttribute("class").contains("all"))
                .map(w -> w.getText())
                .collect(Collectors.toList());
    }

    public QuotesPagesBarPage goToSpecificPageOfQuotes(String page) {
        webDriver.findElement(By.xpath("//div[contains(@class, 'pagination')]/a[text()='" + page + "']")).click();
        return this;
    }

    public List<WebElement> getQuotesRowsFromPage() {
        List<WebElement> quotesRows = new ArrayList<>();
        return new QuotesPage(webDriver).getQuotesTable()
                .findElements(By.tagName("tr")).stream()
                .filter(w -> !w.getAttribute("class").contains("adv")).collect(Collectors.toList());
    }
}
