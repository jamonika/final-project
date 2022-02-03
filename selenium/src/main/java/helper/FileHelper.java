package helper;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

import static java.nio.file.Files.readString;

@Slf4j
public class FileHelper {

    public void saveDataInJsonFormat(String data, Path outputFilePath) {
        log.info("Save data in JSON format in path " + outputFilePath);
        File filePath = new File(String.valueOf(outputFilePath));
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readDataFromJsonFile(Path inputFilePath, Object obj) {
        log.info("Read data from JSON file from path " + inputFilePath);
        Gson gson = new Gson();
        try {
            return gson.fromJson(readString(inputFilePath), (Type) obj);
        } catch (IOException e) {
            System.out.println("Attempt to read the file failed");
        }
        return null;
    }

    public void saveDataInCsvFormat(ArrayList<String> lines, Path path) {
        log.info("Save data in csv format");
        try {
            File file = new File(String.valueOf(path.resolve(String.format("results_%s.csv", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)))));
            PrintWriter printerWriter = new PrintWriter(file);
            for (String line : lines)
                printerWriter.println(line);
            printerWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not saved due to incorrect path");
        }
    }
}
