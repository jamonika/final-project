package dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Stocks {
    private List<Stock> stocksData;

    public ArrayList<String> mapStockListToStringList() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add(Stock.getHeaders());
        for (Stock s : stocksData) {
            lines.add(s.toString());
        }
        return lines;
    }
}
