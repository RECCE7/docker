package metrics.plugins.telnet;

import java.util.Map;

/**
 * Created by jesse on 4/29/16.
 */
public class TelnetMetric {
    public Long timeStamp;
    public double sessionDuration;
    public Map<String, Map<String, String>> sessionPropertyCounts;
    public String inputType;
    public String userInput;
    public String peerAddress;
    public String session;
}
