package dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        return "Time,Price";
    }
}
