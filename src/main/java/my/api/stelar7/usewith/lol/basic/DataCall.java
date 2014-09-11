package my.api.stelar7.usewith.lol.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import my.api.stelar7.usewith.lol.L4J;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

@Setter
@Getter
@Log
public class DataCall
{

    URLEndpoint             urlEndpoint;
    List<?>                 data;
    boolean                 blockWhileLimited = true;
    boolean                 verbose           = false;
    boolean                 error             = false;
    HashMap<String, Object> urlParams         = new HashMap<>();

    /**
     * Sends the call to the limiter, executing it when possible
     *
     * @return the String from the result
     * @throws Exception
     */
    public String doCall() throws Exception
    {
        String URL = "https://";
        final StringBuilder items = new StringBuilder();
        if (this.data != null)
        {
            for (final Object s : this.data)
            {
                items.append(s.toString().toLowerCase().replaceAll(" ", "")).append(",");
            }
            items.deleteCharAt(items.length() - 1);
        }
        if (!this.isToStatic())
        {
            URL += L4J.getRegion().getServer();
            if (this.blockWhileLimited)
            {
                L4J.getRateLimiter().get(L4J.getRegion()).acquire();
            } else
            {
                if (!L4J.getRateLimiter().get(L4J.getRegion()).tryAcquire()) { throw new LibraryException(LibraryException.Type.RATE_LIMIT); }
            }
        } else
        {
            URL += Server.GLOBAL.getServer();
        }
        URL += "/" + this.parseEndpoint(this.urlEndpoint.getValue()).replace("{data}", items.toString()) + "?api_key=" + L4J.getAPIKey();
        for (final String s : this.urlParams.keySet())
        {
            URL += "&" + s + "=" + this.urlParams.get(s);
        }
        if (this.verbose)
        {
            DataCall.log.info(URL);
        }
        final HttpClient client = HttpClientBuilder.create().build();
        final HttpResponse response = client.execute(new HttpGet(URL));
        if (response.getStatusLine().getStatusCode() != 200)
        {
            LibraryException.lastError = response.getStatusLine().getStatusCode();
            this.error = true;
            return null;
        }
        final BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        final StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null)
        {
            result.append(line);
        }
        return result.toString();
    }

    private boolean isToStatic()
    {
        return this.urlEndpoint.toString().startsWith("api/lol/static-data");
    }

    private String parseEndpoint(final String s)
    {
        return s.replace("{region}", L4J.getRegion().name().toLowerCase()).replace("{version}", VersionChecker.getFor(this.urlEndpoint));
    }
}
