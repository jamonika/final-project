package service;

import configuration.RestAssuredConfiguration;
import dto.StockPriceHistoryList;
import io.restassured.response.Response;

import java.util.List;

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

    public List<List<Double>> getStockPriceHistory(String date_from, String date_to, String symbol, boolean intraday, String type) {
        return getStockPriceHistoryResponse(date_from, date_to, symbol, intraday, type)
                .getBody()
                .as(StockPriceHistoryList.class)
                .getHistoryList();
    }
}
