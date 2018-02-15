package application.core.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FReader {


    public static JSONObject readJSON(String path) {
        try {
            JSONTokener jt = new JSONTokener(new FileInputStream(path));
            return new JSONObject(jt);
        } catch (JSONException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
