import helper.FileHelper;
import helper.StockHelper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.StockService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static helper.StockHelper.STOCK_SYMBOLS_PATH;

public class GettingStockMarkings {

    @DataProvider(name = "stockSymbolsData")
    public Iterator<Object[]> getStockSymbolsData() {
        FileHelper fileHelper = new FileHelper();
        List<Object[]> dataList = new ArrayList<>();
        List<String> symbols = fileHelper.readDataFromFile(STOCK_SYMBOLS_PATH);
        symbols.stream().forEach(s -> dataList.add(new Object[]{s}));
        return dataList.iterator();
    }


    @Test(dataProvider = "stockSymbolsData")
    public void downloadStockMarkings(String symbol) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowMinusHalfYear = now.minusMonths(6);
        long toInput = now.toInstant(ZoneOffset.UTC).toEpochMilli();
        long fromInput = nowMinusHalfYear.toInstant(ZoneOffset.UTC).toEpochMilli();

        String date_from = String.valueOf(fromInput);
        String date_to = String.valueOf(toInput);
        boolean intraday = false;
        String type = "area";

        StockService stockService = new StockService();
        List<List<Double>> stockPriceHistory = stockService.getStockPriceHistory(date_from, date_to, symbol, intraday, type);

        StockHelper stockHelper = new StockHelper();
        stockHelper.saveStockPriceHistory(stockPriceHistory, symbol);
    }
}
