package helper;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;
import static java.nio.file.Files.readString;

public class FileHelper {

    public Path saveDataInCsvFormat(ArrayList<String> lines, String stockName) {
        try {
            File file = new File(String.format("%s.csv", stockName));
            PrintWriter printerWriter = new PrintWriter(file);
            for (String line : lines)
                printerWriter.println(line);
            printerWriter.close();
            return file.toPath().toAbsolutePath();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> readDataFromFile(Path inputFilePath) {
        try {
            return readAllLines(inputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
