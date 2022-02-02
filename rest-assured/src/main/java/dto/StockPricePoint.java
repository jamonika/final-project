package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockPricePoint {
    private Long time;
    private Double price;

    @Override
    public String toString() {
        return  time + "," + price;
    }

    public static String getHeaders() {
        return "Time, Price";
    }
}
