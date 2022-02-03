package page.objects;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Slf4j
public class StocksListPage extends Page {

    @FindBy(css = "#boxQuotes table tbody")
    private WebElement stocksTable;

    public StocksListPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public String getUrl() {
        return null;
    }

    public List<WebElement> getStockRowsFromPage() {
        log.info("Get table with stocks data");
        waitForElement(stocksTable);
        return stocksTable.findElements(By.tagName("tr")).stream()
                .filter(w -> !w.getAttribute("class").contains("adv")).collect(Collectors.toList());
    }
}
