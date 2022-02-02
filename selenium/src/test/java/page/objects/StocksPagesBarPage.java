package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class StocksPagesBarPage extends Page {

    public static final String BASE_STOCKS_URL = "https://www.bankier.pl/gielda/notowania/akcje";

    @FindBy(css = ".pagination.top a")
    private List<WebElement> stocksPagesBar;

    public StocksPagesBarPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getUrl() {
        return BASE_STOCKS_URL;
    }

    public List<String> getAllStockPagesTexts() {
        return stocksPagesBar.stream()
                .filter(w -> !w.getAttribute("class").contains("all"))
                .map(w -> w.getText())
                .collect(Collectors.toList());
    }

    public StocksListPage goToSpecificPageOfStocks(String page) {
        String locator = String.format("//div[contains(@class, 'pagination')]/a[text()='%s']", page);
        webDriver.findElement(By.xpath(locator)).click();
        return new StocksListPage(webDriver);
    }

}
