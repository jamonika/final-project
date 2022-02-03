package tests;

import dto.Stock;
import helper.FindingLatestStocksDataHelper;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertTrue;

@Slf4j
public class CheckIfStocksPriceRaisedTest {

    @DataProvider(name = "stockData")
    private static Iterator<Object[]> returnStockData() {
        FindingLatestStocksDataHelper flsdh = new FindingLatestStocksDataHelper();
        List<Object[]> dataList = new ArrayList<>();
        List<Stock> stocks = flsdh.getMostRecentTimeResults().getStocksData();
        stocks.stream().forEach(s -> dataList.add(new Object[]{s}));
        return dataList.iterator();
    }

    @Test(dataProvider = "stockData")
    @Description("The goal of this test is to check if stock price raised according to latest saved report")
    public void checkIfStockPriceRaised(Stock stock) {
        log.info("Check if " + stock.getName() + " price raised acorrding to latest saved report");
        Double change = Double.valueOf(stock.getChange());
        assertTrue(change > 0, "Stock price decreased");
    }

}
