package helper;

import dto.Stocks;

import java.io.File;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindingLatestStocksDataHelper {

    public static final Path STOCK_FILES_PATH = Path.of("src", "main", "resources", "stock_data");

    public Stocks getMostRecentTimeResults() {
        File filePath = new File(String.valueOf(STOCK_FILES_PATH));
        File[] filesInDirectory = filePath.listFiles();
        List<String> fileNames = Arrays.asList(filesInDirectory).stream().map(f -> f.getName()).collect(Collectors.toList());
        List<String> dateList = fileNames.stream().map(s -> {
            String[] splitted = s.split("[-.]");
            return splitted[1];
        }).collect(Collectors.toList());
        sortDates(dateList);
        File mostRecentTimeResultFile = Arrays.stream(filesInDirectory).filter(f -> f.getName().contains(dateList.get(0))).findFirst().get();

        return (Stocks) new FileHelper().readDataFromJsonFile(mostRecentTimeResultFile.toPath(), Stocks.class);
    }

    private void sortDates(List<String> dateList) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
        Collections.sort(dateList, ((Comparator<String>) (o1, o2) -> {
            try {
                return dateFormat.parse(o1).compareTo(dateFormat.parse(o2));
            } catch (ParseException e) {
                System.out.println("Invalid file in directory. File has been omitted.");
            }
            return 0;
        }).reversed());
    }
}
