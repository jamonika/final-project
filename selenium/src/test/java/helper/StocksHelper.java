package helper;

import dto.Stock;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.objects.StocksPagesBarPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class StocksHelper {

    public List<Stock> getStocks(StocksPagesBarPage stocksPagesBarPage, List<String> quotesBarTexts) {
        List<Stock> allStocks = new ArrayList<>();

        for (String s : quotesBarTexts) {
            List<WebElement> quotesRows = stocksPagesBarPage.goToSpecificPageOfStocks(s).getStockRowsFromPage();
            for (WebElement r : quotesRows) {
                allStocks.add(mapRowToStockElement(r));
            }
        }
        return allStocks;
    }

    private Stock mapRowToStockElement(WebElement row) {
        List<WebElement> stockData = row.findElements(By.tagName("td"));
        String name = stockData.get(0).findElement(By.tagName("a")).getText();
        stockData.remove(0);
        List<String> stockDataStrings = stockData.stream().map(td -> td.getText()).collect(Collectors.toList());

        int i = 0;
        Stock stock = Stock.builder()
                .name(name)
                .price(stockDataStrings.get(0).replace(",", ".").replace(" ", ""))
                .change(stockDataStrings.get(++i).replace(",", ".").replace(" ", ""))
                .percentageChange(stockDataStrings.get(++i).replace(",", ".").replace(" ", ""))
                .transactionsNumber(stockDataStrings.get(++i).replace(",", ".").replace(" ", ""))
                .volume(stockDataStrings.get(++i).replace(",", ".").replace(" ", ""))
                .opening(stockDataStrings.get(++i).replace(",", ".").replace(" ", ""))
                .max(stockDataStrings.get(++i).replace(",", ".").replace(" ", ""))
                .min(stockDataStrings.get(++i).replace(",", ".").replace(" ", ""))
                .time(stockDataStrings.get(++i))
                .build();

        return stock;
    }
}
