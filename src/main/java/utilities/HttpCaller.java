package utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import pages.Page;

import javax.net.ssl.SSLContext;
import java.io.IOException;

public class HttpCaller {

    private Logger LOG = Logger.getLogger(getClass());
    private HttpResponse response = null;
    private String responseString = null;
    private String responseJsonString = null;
    private int responseStatus;
    private JsonElement responseJson = null;

    public void get(String baseUrl) {

        cleanResponse();
        CloseableHttpClient client = createApacheClient();
        HttpGet get = new HttpGet(baseUrl);

        try {
            response = client.execute(get);

            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();

            if (entity == null) {
                throw new ClientProtocolException("Response contains no content!");
            }

            responseStatus = statusLine.getStatusCode();

            LOG.info("GET request made to url: " + get.getURI());
            LOG.info("Response Code: " + statusLine.getStatusCode());

            responseStatus = statusLine.getStatusCode();
            responseString = EntityUtils.toString(response.getEntity());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jsonParser = new JsonParser();

            try {
                responseJson = jsonParser.parse(responseString);
                responseJsonString = gson.toJson(responseJson);
            } catch (Exception e) {
                LOG.info("Response was not in JSON format!");
            }

            if (responseStatus > 299) {
                LOG.info("Error content:\n" + Page.truncateStringByCharacters(responseJsonString, 1028));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public HttpResponse getResponse() {
        return response;
    }

    public String getResponseString() {
        return responseString;
    }

    public String getResponseJsonString() {
        return responseJsonString;
    }

    public JsonElement getResponseJson() {
        return responseJson;
    }

    public int getResponseCode() {
        return responseStatus;
    }

    public void cleanResponse() {
        response = null;
        responseString = null;
        responseJsonString = null;
        responseJson = null;
        responseStatus = 0;
    }

    public static CloseableHttpClient createApacheClient() {
        try {
            SSLContext sslContext = SSLContexts
                    .custom()
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .build();

            SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(sslContext,
                    new DefaultHostnameVerifier());

            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("https", sslConnectionFactory)
                    .register("http", new PlainConnectionSocketFactory())
                    .build();

            HttpClientBuilder builder = HttpClientBuilder.create();
            builder.setSSLSocketFactory(sslConnectionFactory);
            builder.setConnectionManager(new PoolingHttpClientConnectionManager(registry));

            return builder.build();
        } catch (Exception ex) {

            return null;
        }
    }
}
