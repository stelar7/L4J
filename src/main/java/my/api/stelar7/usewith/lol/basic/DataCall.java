package my.api.stelar7.usewith.lol.basic;

import java.io.UnsupportedEncodingException;
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
    boolean                 verbose           = false;
    boolean                 blockWhileLimited = true;
    LibraryException        errorData         = null;
    HashMap<String, Object> urlParams         = new HashMap<>();

    public boolean hasError()
    {
        return errorData != null;
    }

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

    private String buildData()
    {
        final StringBuilder items = new StringBuilder();
        if (this.data != null)
        {
            for (final Object s : this.data)
            {
                try
                {
                    items.append(URLEncoder.encode(s.toString().toLowerCase().replaceAll(" ", ""), "UTF-8")).append(",");
                } catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
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
    public String doCall() throws LibraryException
    {
        final StringBuilder URL = new StringBuilder();
        if (!urlEndpoint.getValue().startsWith("http"))
        {
            URL.append("https://");
            if (!this.isToStatic())
            {
                URL.append(L4J.getRegion().getServer());
                this.applyRateLimit();
            } else
            {
                URL.append(Server.GLOBAL.getServer());
            }
            URL.append("/");
        }
        URL.append(replaceData(replaceVersion(replaceRegion(this.urlEndpoint.getValue()))));
        URL.append(this.getAPIkey()).append(this.getParameters());
        if (L4J.verbose || this.verbose)
        {
            DataCall.log.info(URL.toString());
        }
        final HttpResponse response = HttpClient.execute(new GET(URL.toString()), 1);
        if (response.getStatusCode() != 200)
        {
            this.errorData = new LibraryException(response.getStatusCode());
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

    private String getParameters()
    {
        final StringBuilder params = new StringBuilder();
        for (final String s : this.urlParams.keySet())
        {
            try
            {
                params.append("&").append(s).append("=").append(URLEncoder.encode(this.urlParams.get(s).toString(), "UTF-8"));
            } catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        return params.toString();
    }

    private boolean isToStatic()
    {
        return this.urlEndpoint.toString().startsWith("api/lol/static-data");
    }

    private String replaceRegion(String s)
    {
        return s.replace("{region}", L4J.getRegion().name().toLowerCase());
    }

    private String replaceVersion(String s)
    {
        return s.replace("{version}", VersionChecker.getFor(this.urlEndpoint));
    }

    private String replaceData(final String s)
    {
        return s.replace("{data}", this.buildData());
    }
}
