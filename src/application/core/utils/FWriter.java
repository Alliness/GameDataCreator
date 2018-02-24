package application.core.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FWriter {

    public static void save(String content, File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
