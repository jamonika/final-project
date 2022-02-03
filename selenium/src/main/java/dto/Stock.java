package dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Stock {
    private String name;
    private String price;
    private String change;
    private String percentageChange;
    private String transactionsNumber;
    private String volume;
    private String opening;
    private String max;
    private String min;
    private String time;

    public String returnSelectedParameter(Integer parameter) {
        switch (parameter) {
            case 1:
                return price;
            case 2:
                return change;
            case 3:
                return  percentageChange;
            case 4:
                return transactionsNumber;
            case 5:
                return volume;
            case 6:
                return opening;
            case 7:
                return max;
            case 8:
                return min;
        }
        return null;
    }

    public String toString() {
        return String.format(
                "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                name,
                price,
                change,
                percentageChange,
                transactionsNumber,
                volume,
                opening,
                max,
                min,
                time
        );
    }

    public static String getHeaders() {
        return "Name, Price, Change, Percentage Change, Transactions Number, Volume, Opening, Max, Min, Time";
    }


}

