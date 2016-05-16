import metrics.GraphiteMetrics;
import metrics.plugins.http.HTTPMetrics;
import metrics.plugins.telnet.TelnetMetrics;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jesse on 4/24/16.
 */
public class GraphitePlayground {
    private final static int UPDATE_INTERVAL = 60;
    private final static List<GraphiteMetrics> HONEY_PLUGINS = new ArrayList<>();

    public static void main (String [] args) {
        HONEY_PLUGINS.add(new TelnetMetrics("telnet.telnet", 22));
        HONEY_PLUGINS.add(new TelnetMetrics("telnet.telnet2", 23));
        HONEY_PLUGINS.add(new HTTPMetrics("http.HTTP", 80));
        HONEY_PLUGINS.add(new HTTPMetrics("http.HTTP", 443));

        while (true) {
            Long timestamp = System.currentTimeMillis() / 1000;
            for (GraphiteMetrics pluginMetric : HONEY_PLUGINS) {
                pluginMetric.writeMetrics(timestamp);
            }
            try {
                Thread.sleep(UPDATE_INTERVAL * 1000);
            } catch (InterruptedException e) {
                System.out.println("Error forcing sleep between carbon updates");
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
