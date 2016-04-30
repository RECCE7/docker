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

    private static Map<String, String> getSessionFields (List<String> sessionFields) {
        return null;
    }

    private static JSONArray getItems (JSONObject responseData) {
        return (JSONArray) responseData.get("items");
    }

    private static JSONArray getSessionItems (JSONObject portItem) {
        return (JSONArray) portItem.get("session_items");
    }

    private static  Map<String, Map<String, Integer>> setPropertyCounts(JSONArray items, List<String> sessionFields) {
        Map<String, Map<String, Integer>> propertyCountsMap = new HashMap<>();
        for (Object item : items) {
            JSONArray sessionItems = getSessionItems((JSONObject) item);

            for (Object sessionItem : sessionItems) {
                JSONObject sessionObject = (JSONObject) sessionItem;
//                String peerAddress = (String)sessionObject.get("peerAddress");
//                String userInput = (String)sessionObject.get("user_input");
//                String inputType = (String)sessionObject.get("input_type");

                for (String field : sessionFields) {

                    Map<String, Integer> valueCountsForProperty = propertyCountsMap.get(field);
                    if (valueCountsForProperty == null) {
                        valueCountsForProperty = new HashMap<>();
                        propertyCountsMap.put(field, valueCountsForProperty);
                    }
                    if (sessionObject.get(field) != null) {
                        String key = sessionObject.get(field).toString();
                        Integer propCount = valueCountsForProperty.get(key);
                        if (propCount == null) {
                            valueCountsForProperty.put(key, 1);
                        } else {
                            valueCountsForProperty.put(key, ++propCount);
                        }
                    }
                }
            }
        }
        return propertyCountsMap;
    }

    public static Map<String, Map<String, Integer>> parse(String json, List<String> sessionFields) {
        try {
            JSONParser parser = new JSONParser();
            Object resultObject = parser.parse(json);

//            if (resultObject instanceof JSONArray) {
//                JSONArray array = (JSONArray) resultObject;
//                for (Object object : array) {
//                    JSONArray portItems = getItems((JSONObject) object);
//
//                }
//            } else
            if (resultObject instanceof JSONObject) {
//                JSONArray portItems =
                return setPropertyCounts(getItems((JSONObject) resultObject), sessionFields);
            }
        } catch (ParseException e) {
            //ToDo: Handle exception
            e.printStackTrace();
        }
        return null;
    }
}
