package helper;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;

import static java.nio.file.Files.readString;
import static org.testng.AssertJUnit.fail;

public class FileHelper {

    public void saveDataInJsonFormat(String data, Path outputFilePath) {
        File filePath = new File(String.valueOf(outputFilePath));
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            fail("File operation failed");
        }
    }

    public Object readDataFromJsonFile(Path inputFilePath, Object obj) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(readString(inputFilePath), (Type) obj);
        } catch (IOException e) {
            fail("File is invalid and not readable");
        }
        return null;
    }
}
