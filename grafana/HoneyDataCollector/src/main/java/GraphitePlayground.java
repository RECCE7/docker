import metrics.GraphiteMetrics;
import metrics.plugins.telnet.TelnetMetrics;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jesse on 4/24/16.
 */
public class GraphitePlayground {
    private final static int UPDATE_INTERVAL = 1;
    private final static List<GraphiteMetrics> HONEY_PLUGINS = new ArrayList<>();

    public static void main (String [] args) {
        HONEY_PLUGINS.add(new TelnetMetrics("telnet", 23));
        HONEY_PLUGINS.add(new TelnetMetrics("telnetTwo", 8023));

        while (true) {
            for (GraphiteMetrics pluginMetric : HONEY_PLUGINS) {
                pluginMetric.writeMetrics();
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
