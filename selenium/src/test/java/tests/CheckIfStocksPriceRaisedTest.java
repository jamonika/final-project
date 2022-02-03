package tests;

import dto.Stock;
import helper.FindingLatestStocksDataHelper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;


public class CheckIfStocksPriceRaisedTest {

    @DataProvider(name = "stockData")
    private static Iterator<Object[]> returnStockData() {
        FindingLatestStocksDataHelper flsdh = new FindingLatestStocksDataHelper();
        List<Object[]> dataList = new ArrayList<>();
        List<Stock> stocks = flsdh.getMostRecentTimeResults().getStocksData();
        stocks.stream().forEach(s -> dataList.add(new Object[]{s.getName(), s.getChange(), s}));
        return dataList.iterator();
    }

    @Test(dataProvider = "stockData")
    public void checkIfStockPriceRaised(String name, String changeStr, Stock stock) {
        Double change = Double.valueOf(changeStr);
        assertTrue(change > 0, "Stock price decreased");
    }

}
