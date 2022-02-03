package tests;

import com.google.gson.Gson;
import configuration.BasicTest;
import dto.Stock;
import dto.Stocks;
import helper.FileHelper;
import helper.StocksHelper;
import org.testng.annotations.Test;
import page.objects.PrivacyAndPolicyPage;
import page.objects.StocksPagesBarPage;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DownloadStocksData extends BasicTest {

    private Gson gson = new Gson();
    public static final Path OUTPUT_STOCK_FILES_PATH = Path.of("stock_data");

    @Test(priority = 1)
    public void retrieveStocksData() {
        StocksPagesBarPage stocksPagesBarPage = new StocksPagesBarPage(webDriver);
        stocksPagesBarPage.openPage();
        PrivacyAndPolicyPage privacyAndPolicyPage = new PrivacyAndPolicyPage(webDriver);
        privacyAndPolicyPage.acceptPrivacyAndPolicy();

        List<String> stocksPagesBarTexts = stocksPagesBarPage.getAllStockPagesTexts();

        StocksHelper stocksHelper = new StocksHelper();
        List<Stock> allStocks = stocksHelper.getStocks(stocksPagesBarPage, stocksPagesBarTexts);

        Stocks stocks = new Stocks();
        stocks.setStocksData(allStocks);

        String dataJson = gson.toJson(stocks);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        String filename = "stocks_data-" + dtf.format(now) + ".json";
        FileHelper sfh = new FileHelper();
        sfh.saveDataInJsonFormat(dataJson, OUTPUT_STOCK_FILES_PATH.resolve(filename));
    }

}
