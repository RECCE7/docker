package metrics.plugins.telnet;

import metrics.GraphiteMetrics;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.carboncache.CarbonWriter;
import utils.reportserver.ReportRequest;
import utils.reportserver.RequestParser;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by jesse on 4/28/16.
 */
public class TelnetMetrics implements GraphiteMetrics {
    private final static List<String> TELNET_SESSION_ITEM_FIELDS = new ArrayList<>();
    private final String pluginName;
    private final int pluginPort;

    public TelnetMetrics(String pluginName, int pluginPort) {
        TELNET_SESSION_ITEM_FIELDS.add("localAddress");
        TELNET_SESSION_ITEM_FIELDS.add("eventDateTime");
        TELNET_SESSION_ITEM_FIELDS.add("peerAddress");
        TELNET_SESSION_ITEM_FIELDS.add("user_input");
        TELNET_SESSION_ITEM_FIELDS.add("input_type");
        this.pluginName = pluginName;
        this.pluginPort = pluginPort;
    }

    public void writeMetrics() {
        SimpleDateFormat test = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S");
        telnet();
    }

//    private Object

//    private

    private TelnetMetric [] createTelnetMetrics (JSONArray jsonData) {
        TelnetMetric [] telnetData = new TelnetMetric[jsonData.size()];

        for (int i = 0; i < jsonData.size(); i++) {
            JSONObject telnetObject = (JSONObject)jsonData.get(i);
            String durationTimeCode = (String)telnetObject.get("duration");
            JSONArray sessionItems = (JSONArray)telnetObject.get("session_items");
            for (Object sessionItem : sessionItems) {

            }

        }
        return telnetData;
    }

    private String telnet() {
        String result = ReportRequest.requestByPort(this.pluginPort);
//        Map<String, Map<String, Integer>> propertyCounts = RequestParser.parse(result, TELNET_SESSION_ITEM_FIELDS);
        JSONArray items = RequestParser.parse(result, TELNET_SESSION_ITEM_FIELDS);

//        if (propertyCounts != null) {
//            for (String property : propertyCounts.keySet()) {
//                ArrayList<String> messages = new ArrayList<>();
//                Long timestamp = System.currentTimeMillis() / 1000;
//                for (String valueProp : propertyCounts.get(property).keySet()) {
//                    try {
//                        String sentMessage = this.pluginName + "." + property + "." + valueProp.replace(".", "-") + " " + propertyCounts.get(property).get(valueProp) + " " + timestamp + "\n";
//                        System.out.print(sentMessage);
//                        messages.add(sentMessage);
//                    } catch (Exception e) {
//                        // TODO: handle exception
//                        System.out.println(e.getMessage());
//                        e.printStackTrace();
//                    }
//                }
//                CarbonWriter.writeData(messages);
//            }
//        }
        return null;
    }
}