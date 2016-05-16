package metrics.plugins.http;

import metrics.GraphiteMetrics;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.carboncache.CarbonWriter;
import utils.reportserver.ReportRequest;
import utils.reportserver.RequestParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jesse on 4/28/16.
 */
public class HTTPMetrics implements GraphiteMetrics {
    private final String pluginName;
    private final int pluginPort;
    private final List<String> carbonMessages = new ArrayList<>();

    public HTTPMetrics(String pluginName, int pluginPort) {
        this.pluginName = pluginName;
        this.pluginPort = pluginPort;
    }

    public void writeMetrics(Long timeStamp) {
        String result = ReportRequest.requestByPort(this.pluginPort);
        JSONArray items = RequestParser.parse(result);
        for (Map.Entry<String, Integer> property : createHTTPMetrics(items, timeStamp).entrySet()) {
            carbonMessages.add(CarbonWriter.generateMetricString(this.pluginName, property.getKey(), property.getValue() + "", timeStamp));
        }
        CarbonWriter.writeData(carbonMessages);
    }

    private Map<String, Integer> createHTTPMetrics(JSONArray jsonData, Long timeStamp) {
        Map<String, Integer> propertyCounts = new HashMap<>();

        for (int i = 0; i < jsonData.size(); i++) {
            JSONObject telnetObject = (JSONObject)jsonData.get(i);
            double duration = RequestParser.convertDurationTimecode((String) telnetObject.get("duration"));
            String peerAddress = ((String) telnetObject.get("peer_address")).replaceAll("\\.", "-");
            String sessionId = (String) telnetObject.get("session");
            JSONArray sessionItems = (JSONArray)telnetObject.get("session_items");
            int numberOfSessionCommands = sessionItems.size();
            carbonMessages.add(CarbonWriter.generateMetricString(this.pluginName, "sessisons." + peerAddress + "." + sessionId + ".duration", duration + "", timeStamp));
            carbonMessages.add(CarbonWriter.generateMetricString(this.pluginName, "sessisons." + peerAddress + "." + sessionId + ".numberOfCommands", numberOfSessionCommands + "", timeStamp));
            for (Object sessionItem : sessionItems) {
                JSONObject sessionItemJson = (JSONObject) sessionItem;
                String path = ((String)sessionItemJson.get("path")).replaceAll("\\.", "-")
                                                                   .replaceAll("/", "")
                                                                   .replaceAll("\\s", "_");
                String command = (String)sessionItemJson.get("command");
                String propertyCountsKey = "commands." + (command != null && command.length() > 0 ?  command : "empty") + "." + (path.length() > 0 ? path : "empty");
                propertyCountsKey = propertyCountsKey.replaceAll("\\s", "_");
                Integer count = propertyCounts.get(propertyCountsKey);
                if (count == null) {
                    propertyCounts.put(propertyCountsKey, 1);
                }
                else {
                    propertyCounts.put(propertyCountsKey, count + 1);
                }
            }

        }
        return propertyCounts;
    }
}
