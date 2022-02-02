import dto.StockPricePoint;
import helper.FileHelper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.StockService;

import java.nio.file.Path;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GettingStockMarkings {
    private static final Path STOCK_SYMBOLS_PATH = Path.of( "src", "main", "resources", "stock_symbols");

    @DataProvider(name ="stockSymbolsData")
    public Iterator<Object[]> getStockSymbolsData() {
        FileHelper fileHelper = new FileHelper();
        List<Object[]> dataList = new ArrayList<>();
        List<String> symbols = fileHelper.readDataFromFile(STOCK_SYMBOLS_PATH);
        symbols.stream().forEach(s -> dataList.add(new Object[]{s}));
        return dataList.iterator();
    }


    @Test (dataProvider = "stockSymbolsData")
    public void downloadStockMarkings(String symbol) {
        StockService stockService = new StockService();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowMinusHalfYear = now.minusMonths(6);
        long toInput = now.toInstant(ZoneOffset.UTC).toEpochMilli();
        long fromInput = nowMinusHalfYear.toInstant(ZoneOffset.UTC).toEpochMilli();

        String date_from = String.valueOf(fromInput);
        String date_to = String.valueOf(toInput);
        boolean intraday = false;
        String type = "area";

        List<List<Double>> stockPriceHistory = stockService.getStockPriceHistory(date_from, date_to, symbol, intraday, type);

        List<StockPricePoint> listOfAllPoints = new ArrayList<>();
        for (List<Double> l : stockPriceHistory) {
            listOfAllPoints.add(StockPricePoint.builder().time(l.get(0).longValue()).price(l.get(1)).build());
        }

        FileHelper fileHelper = new FileHelper();
        fileHelper.saveDataInCsvFormat(mapStockPricePointsToStringList(listOfAllPoints), symbol);

        System.out.println();
    }

    public ArrayList<String> mapStockPricePointsToStringList(List<StockPricePoint> listOfAllPoints) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(StockPricePoint.getHeaders());
        for (StockPricePoint s : listOfAllPoints) {
            lines.add(s.toString());
        }
        return lines;
    }
}
