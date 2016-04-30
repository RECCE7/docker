package utils.reportserver;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by jesse on 4/28/16.
 */
public class ReportRequest {
    private final static String REPORT_SERVER_HOST = "pi";
    private final static int REPORT_SERVER_PORT = 8080;
    private final static String REPORT_SERVER_BASE_URL = "/v1/analytics/";
    private final static String PORTS = "ports/";

    public static String requestByPort (int port) {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            URIBuilder builder = new URIBuilder();
            builder.setScheme("http").setHost(REPORT_SERVER_HOST).setPort(REPORT_SERVER_PORT).setPath(REPORT_SERVER_BASE_URL + PORTS + port).setParameter("days", "1");
            HttpGet request = new HttpGet(builder.build());
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");

            return json;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
