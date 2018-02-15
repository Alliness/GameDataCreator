package application.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.util.Objects;

abstract public class Serializable {

    public JSONObject serialize() {

        try {
            Gson gson = new GsonBuilder().serializeNulls().create();
            return new JSONObject(gson.toJson(this));

        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
    }

    public static JSONObject serialize(Object object){
        try {
            Gson gson = new GsonBuilder().serializeNulls().create();
            return new JSONObject(gson.toJson(object));

        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
    }

    public JSONObject serializeWithoutNulls() {

        try {
            Gson gson = new GsonBuilder().create();
            return new JSONObject(gson.toJson(this));

        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
    }

    public static <T> T deserialize(String jsonString, Class<T> type) {
        try {
            return new Gson().fromJson(jsonString, type);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static <T> T deserialize(JSONObject json, Class<T> type) {
        try {
            return new Gson().fromJson(json.toString(), type);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public <T extends Serializable> boolean typeOf(Class<T> type) {
        return Objects.equals(type, this.getClass());
    }
}
