package dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;

@Data
@AllArgsConstructor
public class StockPriceHistoryList {

    @SerializedName("main")
    public List<List<Double>> historyList;
}
