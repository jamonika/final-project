package service;

import configuration.RestAssuredConfiguration;
import dto.StockPriceHistoryList;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class StockService extends RestAssuredConfiguration {

    public static final String GET_STOCK_PRICE_HISTORY_DATA = "new-charts/get-data";

    public Response getStockPriceHistoryResponse(String date_from, String date_to, String symbol, boolean intraday, String type) {
        return getRequestSpecification()
                .queryParam("date_from", date_from)
                .queryParam("date_to", date_to)
                .queryParam("symbol", symbol)
                .queryParam("intraday", intraday)
                .queryParam("type", type)
                .get(GET_STOCK_PRICE_HISTORY_DATA);
    }

    @Step("Getting stock price history")
    public List<List<Double>> getStockPriceHistory(String date_from, String date_to, String symbol, boolean intraday, String type) {
        log.info("Get " + symbol + " stock price history data");
        return getStockPriceHistoryResponse(date_from, date_to, symbol, intraday, type)
                .getBody()
                .as(StockPriceHistoryList.class)
                .getHistoryList();
    }
}
