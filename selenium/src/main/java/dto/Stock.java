package dto;

import lombok.Builder;

import java.util.Date;

@Builder
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
}
