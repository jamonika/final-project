package filter.stocks.runner;

import dto.Stock;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterStocks {
    private Integer parameter;
    private char operator;
    private List<Double> values = new ArrayList<>();


    public void askUserForParameter() {
        System.out.println(
                """
                                Filtering stocks data and getting the list of results in csv format file.
                                        
                                Stock data contains following parameters:
                                1. price
                                2. change
                                3. percentage change
                                4. transactions number
                                5. volume
                                6. opening
                                7. max
                                8. min
                                        
                                Please select the parameter by which you want to filter the data by providing the number of the parameter from above list.
                                If you want to exit program type "exit".
                        """
        );
    }

    public void askUserForOperation() {
        System.out.println("Enter the value and operator to filter data. E.g. <10.3 or >10.3 or =10 or <20>10.");
    }

    public boolean checkIfParameterIsCorrect(String parameter) {
        int parameterNr = 0;
        try {
            parameterNr = Integer.valueOf(parameter);
        } catch (NumberFormatException e) {
            System.out.println("Wrong parameter.");
            return false;
        }
        if (parameterNr >= 0 && parameterNr <= 8) {
            this.parameter = parameterNr;
            return true;
        }
        System.out.println("Wrong parameter.");
        return false;
    }

    public boolean checkIfOperationCorrect(String operation) {
        if (operation == null) throw new RuntimeException();
        char operator = operation.charAt(0);
        if (operator != '<' && operator != '>' && operator != '=') {
            throw new RuntimeException();
        }
        this.operator = operator;

        StringBuilder sb = new StringBuilder(operation);
        String operation2 = sb.deleteCharAt(0).toString();
        String[] splitted = operation2.split(">");

        if (splitted.length == 1) {
            try {
                values.add(Double.valueOf(splitted[0]));
            } catch (NumberFormatException e) {
                return false;
            }

        } else if (splitted.length == 2) {
            try {
                values.add(Double.valueOf(splitted[0]));
                values.add(Double.valueOf(splitted[1]));
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    public boolean checkWhetherToExit(String input) {
        if (input.equals("exit")) {
            return true;
        }
        return false;
    }

    public List<Stock> filterStocksData(List<Stock> stocksInput) {
        return stocksInput.stream()
                .filter(s -> {
                    Double value = Double.valueOf(s.returnSelectedParameter(parameter).replace("%", ""));
                    if (values.size() == 1) {
                        switch (operator) {
                            case '>':
                                return value > values.get(0);
                            case '<':
                                return value < values.get(0);
                            case '=':
                                return value.equals(values.get(0));
                        }
                    }
                    if (values.size() == 2) {
                        return value < values.get(0) && value > values.get(1);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
}
