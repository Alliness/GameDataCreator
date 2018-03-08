package application.core.utils;

import org.json.JSONObject;

public class JSONUtils {


    public static String pretty(JSONObject skeleton) {
        return skeleton.toString(2);
    }
}
