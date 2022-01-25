package tests;

import com.google.gson.Gson;
import configuration.BasicTest;
import dto.Stock;
import dto.Stocks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import page.objects.PrivacyAndPolicyPage;
import page.objects.QuotesPagesBarPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DownloadStocksData extends BasicTest {

    private Gson gson = new Gson();

    @Test
    public void getStocksData() {
        QuotesPagesBarPage quotesPagesBarPage = new QuotesPagesBarPage(webDriver);
        quotesPagesBarPage.openPage();
        PrivacyAndPolicyPage privacyAndPolicyPage = new PrivacyAndPolicyPage(webDriver);
        privacyAndPolicyPage.acceptPrivacyAndPolicy();

        List<String> quotesBarTexts = quotesPagesBarPage.getAllQuotesBarTexts();

        List<Stock> allStocks = getStocks(quotesPagesBarPage, quotesBarTexts);

        Stocks stocks = new Stocks();
        stocks.setStocksData(allStocks);
        String json = gson.toJson(stocks);
    }

    private List<Stock> getStocks(QuotesPagesBarPage quotesPagesBarPage, List<String> quotesBarTexts) {
        List<Stock> allStocks = new ArrayList<>();

        for (String s : quotesBarTexts) {
            List<WebElement> quotesRows = quotesPagesBarPage.goToSpecificPageOfQuotes(s).getQuotesRowsFromPage();
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
                .price(stockDataStrings.get(0))
                .change(stockDataStrings.get(++i))
                .percentageChange(stockDataStrings.get(++i))
                .transactionsNumber(stockDataStrings.get(++i))
                .volume(stockDataStrings.get(++i))
                .opening(stockDataStrings.get(++i))
                .max(stockDataStrings.get(++i))
                .min(stockDataStrings.get(++i))
                .time(stockDataStrings.get(++i))
                .build();

        return stock;
    }

}
