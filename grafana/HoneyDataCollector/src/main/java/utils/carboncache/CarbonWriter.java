package utils.carboncache;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.List;

/**
 * Created by jesse on 4/29/16.
 */
public class CarbonWriter {
    private final static String METRIC_PATH_ROOT = "graphite.carbon.honey.";
    private final static String CARBON_HOST = "docker";
    private final static int CARBON_PLAIN_TEXT_PORT = 2003;

    public static void writeData (List<String> carbonMessages) {
        try {
            Socket socket = new Socket(CARBON_HOST, CARBON_PLAIN_TEXT_PORT);
            Writer writer = new OutputStreamWriter(socket.getOutputStream());
            for (String message : carbonMessages) {
                System.out.println(METRIC_PATH_ROOT + message);
                writer.write(METRIC_PATH_ROOT + message);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeData (String message) {
        try {
            Socket socket = new Socket(CARBON_HOST, CARBON_PLAIN_TEXT_PORT);
            Writer writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(METRIC_PATH_ROOT + message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String generateMetricString (String pluginName, String metricPath, String metricValue, Long timestamp) {

        return pluginName + "." + metricPath + " " + metricValue + " " + timestamp + "\n";
    }
}
