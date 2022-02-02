package filter.stocks.tool;

import dto.Stock;
import dto.Stocks;
import helper.FileHelper;
import helper.FindingLatestStocksDataHelper;

import java.util.List;
import java.util.Scanner;

public class FilterStocksRunner {

    public static void main(String[] args) {
        FilterStocks fs;
        Scanner scanner = new Scanner(System.in);

        String parameter;
        String operation;
        while (true) {
            fs = new FilterStocks();
            do {
                fs.askUserForParameter();
                parameter = scanner.nextLine();
            } while (!fs.checkWhetherToExit(parameter) && !fs.checkIfParameterIsCorrect(parameter));
            if (fs.checkWhetherToExit(parameter)) break;

            do {
                fs.askUserForOperation();
                operation = scanner.nextLine();
            } while (!fs.checkWhetherToExit(operation) && !fs.checkIfOperationCorrect(operation));
            if (fs.checkWhetherToExit(operation)) break;

            FindingLatestStocksDataHelper flsdh = new FindingLatestStocksDataHelper();
            List<Stock> stocks = flsdh.getMostRecentTimeResults().getStocksData();

            Stocks filtered = new Stocks();
            filtered.setStocksData(fs.filterStocksData(stocks));
            FileHelper fh = new FileHelper();
            fh.saveDataInCsvFormat(filtered.mapStockListToStringList());
        }
    }
}
