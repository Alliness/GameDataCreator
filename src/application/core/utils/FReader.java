package application.core.utils;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FReader {

    private static final Logger log = Logger.getLogger(FReader.class);

    public static JSONObject readJSON(String path) {
        try {
            JSONTokener jt = new JSONTokener(new FileInputStream(path));
            return new JSONObject(jt);
        } catch (JSONException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HashMap<String, JSONObject> readAll(String dir) {


        HashMap<String, JSONObject> list = new HashMap<>();
        File             fdir = new File(dir);

        if (!fdir.isDirectory()) {
            log.error("Path '" + dir + "' is not a directory");
            return list;
        }
        File[] files = fdir.listFiles();
        if (files != null) {
            for (File file : files) {
                list.put(file.getName().replace(".json", ""),readJSON(file.getAbsolutePath()));
            }
        }
        return list;
    }
}
