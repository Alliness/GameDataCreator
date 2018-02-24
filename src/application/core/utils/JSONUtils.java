package application.core.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

public class JSONUtils {


    public static String pretty(JSONObject skeleton) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(skeleton);
    }
}
