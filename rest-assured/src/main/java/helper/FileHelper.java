package helper;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class FileHelper {

    public void saveDataInCsvFormat(ArrayList<String> lines, String fileName, Path outputFilePath) {
        try {
            File file = new File(String.valueOf(outputFilePath.resolve(String.format("%s.csv", fileName))));
            PrintWriter printerWriter = new PrintWriter(new FileWriter(file, true));
            for (int i=0; i<lines.size(); i++) {
                    printerWriter.println(lines.get(i));
            }
            printerWriter.close();
        } catch (IOException e) {
            System.out.println("File is not saved due to incorrect path");
        }
    }

    public List<String> readDataFromFile(Path inputFilePath) {
        try {
            return readAllLines(inputFilePath);
        } catch (IOException e) {
            System.out.println("Attempt to read the file failed");
        }
        return null;
    }
}
