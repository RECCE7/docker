package utils.reportserver;

//import com.sun.deploy.net.HttpResponse;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.carboncache.CarbonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jesse on 4/29/16.
 */
public class RequestParser {

    private static JSONArray getItems (JSONObject responseData) {
        return (JSONArray) responseData.get("items");
    }

    public static double convertDurationTimecode (String duration) {
        String [] timeParts = duration.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        double seconds = Double.parseDouble(timeParts[2]);

        return hours * 3600 + minutes * 60 + seconds;
    }

    public static JSONArray parse(String json) {
        try {
            JSONParser parser = new JSONParser();
            Object resultObject = parser.parse(json);
            if (resultObject instanceof JSONObject) {
                return getItems((JSONObject) resultObject);
            }
        } catch (ParseException e) {
            //ToDo: Handle exception
            e.printStackTrace();
        }
        return null;
    }
}
