package helper;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

@Slf4j
public class FileHelper {

    public void saveDataInCsvFormat(ArrayList<String> lines, String fileName, Path outputFilePath) {
        log.info("Save file in csv format in path " + outputFilePath);
        try {
            File file = new File(String.valueOf(outputFilePath.resolve(String.format("%s.csv", fileName))));
            PrintWriter printerWriter = new PrintWriter(new FileWriter(file, true));
            for (int i = 0; i < lines.size(); i++) {
                printerWriter.println(lines.get(i));
            }
            printerWriter.close();
        } catch (IOException e) {
            System.out.println("File is not saved due to incorrect path");
        }
    }

    public List<String> readDataFromFile(Path inputFilePath) {
        log.info("Read data from file from path " + inputFilePath);
        try {
            return readAllLines(inputFilePath);
        } catch (IOException e) {
            System.out.println("Attempt to read the file failed");
        }
        return null;
    }
}
