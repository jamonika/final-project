package helper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.testng.AssertJUnit.fail;

public class SavingFileHelper {

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
}
