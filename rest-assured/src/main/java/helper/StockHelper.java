package helper;

import dto.StockPricePoint;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StockHelper {
    public static final Path STOCK_SYMBOLS_PATH = Path.of("src", "main", "resources", "stock_symbols");
    public static final Path STOCK_HISTORY_DATA_PATH = Path.of("stock-history-data");

    private List<StockPricePoint> mapToStockPricePoints(List<List<Double>> stockPriceHistory) {
        List<StockPricePoint> listOfAllPoints = new ArrayList<>();
        for (List<Double> l : stockPriceHistory) {
            listOfAllPoints.add(StockPricePoint.builder().time(l.get(0).longValue()).price(l.get(1)).build());
        }
        return listOfAllPoints;
    }

    private List<StockPricePoint> filterAlreadyDownloadedData(List<StockPricePoint> listOfAllPoints, String symbol, Path path) {
        Long lastTimestamp = getLastTimestampFromFile(symbol, path);
        if (lastTimestamp == null) {
            return listOfAllPoints;
        }
        return listOfAllPoints.stream().filter(p -> p.getTime() > lastTimestamp).collect(Collectors.toList());
    }

    private Long getLastTimestampFromFile(String symbol, Path path) {
        FileHelper fileHelper = new FileHelper();
        List<String> readFile = fileHelper.readDataFromFile(path.resolve(symbol + ".csv"));
        if (readFile == null || readFile.isEmpty()) {
            return null;
        }
        String lastRow = readFile.get(readFile.size() - 1);
        String lastTimestamp = lastRow.split(",")[0];
        if (lastTimestamp.equals("Time")) {
            return null;
        }
        return Long.valueOf(lastTimestamp);
    }

    private ArrayList<String> mapStockPricePointsToStringList(List<StockPricePoint> listOfFilteredPoints) {
        ArrayList<String> lines = new ArrayList<>();
        for (StockPricePoint s : listOfFilteredPoints) {
            lines.add(s.toString());
        }
        return lines;
    }

    private boolean checkIfStockFileExists(String symbol) {
        FileHelper fileHelper = new FileHelper();
        List<String> response = fileHelper.readDataFromFile(STOCK_HISTORY_DATA_PATH.resolve(symbol + ".csv"));
        return response == null ? false : true;
    }

    public void saveStockPriceHistory(List<List<Double>> stockPriceHistory, String symbol) {
        FileHelper fileHelper = new FileHelper();

        List<StockPricePoint> stockPricePointList = mapToStockPricePoints(stockPriceHistory);

        boolean fileExists = checkIfStockFileExists(symbol);

        ArrayList<String> lines = new ArrayList<>();
        if (!fileExists) {
            lines.add(StockPricePoint.getHeaders());
            lines.addAll(mapStockPricePointsToStringList(stockPricePointList));
        } else {
            List<StockPricePoint> filteredStockList = filterAlreadyDownloadedData(stockPricePointList, symbol, STOCK_HISTORY_DATA_PATH);
            lines.addAll(mapStockPricePointsToStringList(filteredStockList));
        }

        if (!lines.isEmpty()) {
            fileHelper.saveDataInCsvFormat(lines, symbol, STOCK_HISTORY_DATA_PATH);
        }
    }
}
