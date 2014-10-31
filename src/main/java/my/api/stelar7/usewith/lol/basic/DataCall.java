package my.api.stelar7.usewith.lol.basic;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import my.api.stelar7.usewith.lol.L4J;
import my.api.stelar7.usewith.lol.network.GET;
import my.api.stelar7.usewith.lol.network.HttpClient;
import my.api.stelar7.usewith.lol.network.HttpResponse;

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
    LibraryException        errorData         = null;
    HashMap<String, Object> urlParams         = new HashMap<>();

    private void applyRateLimit() throws LibraryException
    {
        if (this.blockWhileLimited)
        {
            L4J.getRateLimiter().get(L4J.getRegion()).acquire();
        } else
        {
            if (!L4J.getRateLimiter().get(L4J.getRegion()).tryAcquire()) { throw new LibraryException(LibraryException.Type.RATE_LIMIT); }
        }
    }

    private String buildData() throws Exception
    {
        final StringBuilder items = new StringBuilder();
        if (this.data != null)
        {
            for (final Object s : this.data)
            {
                items.append(URLEncoder.encode(s.toString().toLowerCase().replaceAll(" ", ""), "UTF-8")).append(",");
            }
            items.deleteCharAt(items.length() - 1);
        }
        return items.toString();
    }

    /**
     * Sends the call to the limiter, executing it when possible
     *
     * @return the String from the result
     * @throws Exception
     */
    public String doCall() throws Exception
    {
        final StringBuilder URL = new StringBuilder("https://");
        if (!this.isToStatic())
        {
            URL.append(L4J.getRegion().getServer());
            this.applyRateLimit();
        } else
        {
            URL.append(Server.GLOBAL.getServer());
        }
        URL.append("/").append(this.parseEndpoint(this.urlEndpoint.getValue())).append(this.getAPIkey()).append(this.getParameters());
        if (this.verbose)
        {
            DataCall.log.info(URL.toString());
        }
        final HttpResponse response = HttpClient.execute(new GET(URL.toString()), 1);
        if (response.getStatusCode() != 200)
        {
            this.errorData = new LibraryException(response.getStatusCode());
            this.error = true;
            return null;
        }
        return response.getBody();
    }

    private String getAPIkey()
    {
        final StringBuilder sb = new StringBuilder("?api_key=");
        sb.append(L4J.getAPIKey());
        return sb.toString();
    }

    private String getParameters() throws Exception
    {
        final StringBuilder params = new StringBuilder();
        for (final String s : this.urlParams.keySet())
        {
            params.append("&").append(s).append("=").append(URLEncoder.encode(this.urlParams.get(s).toString(), "UTF-8"));
        }
        return params.toString();
    }

    private boolean isToStatic()
    {
        return this.urlEndpoint.toString().startsWith("api/lol/static-data");
    }

    private String parseEndpoint(final String s) throws Exception
    {
        return s.replace("{region}", L4J.getRegion().name().toLowerCase()).replace("{version}", VersionChecker.getFor(this.urlEndpoint)).replace("{data}", this.buildData());
    }
}
