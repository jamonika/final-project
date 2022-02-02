package tests;

import com.google.gson.Gson;
import configuration.BasicTest;
import dto.Stock;
import dto.Stocks;
import helper.FileHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import page.objects.PrivacyAndPolicyPage;
import page.objects.QuotesPagesBarPage;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DownloadStocksData extends BasicTest {

    private Gson gson = new Gson();
    public static final Path OUTPUT_STOCK_FILES_PATH = Path.of("src", "main", "resources", "stock_data");

    @Test
    public void retrieveStocksData() {
        QuotesPagesBarPage quotesPagesBarPage = new QuotesPagesBarPage(webDriver);
        quotesPagesBarPage.openPage();
        PrivacyAndPolicyPage privacyAndPolicyPage = new PrivacyAndPolicyPage(webDriver);
        privacyAndPolicyPage.acceptPrivacyAndPolicy();

        List<String> quotesBarTexts = quotesPagesBarPage.getAllQuotesBarTexts();

        List<Stock> allStocks = getStocks(quotesPagesBarPage, quotesBarTexts);

        Stocks stocks = new Stocks();
        stocks.setStocksData(allStocks);

        String dataJson = gson.toJson(stocks);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        String filename = "stocks_data-" + dtf.format(now) + ".json";
        FileHelper sfh = new FileHelper();
        sfh.saveDataInJsonFormat(dataJson, OUTPUT_STOCK_FILES_PATH.resolve(filename));
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
