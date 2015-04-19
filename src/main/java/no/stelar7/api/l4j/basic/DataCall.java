package no.stelar7.api.l4j.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import no.stelar7.api.l4j.L4J;

@Setter
@Getter
@Log
public class DataCall
{

    URLEndpoint             urlEndpoint;
    boolean                 verbose           = false;
    boolean                 blockWhileLimited = true;
    LibraryException        errorData         = null;
    List<?>                 data              = null;
    HashMap<String, Object> urlParams         = new HashMap<String, Object>();
    HashMap<String, Object> extraData         = new HashMap<String, Object>();

    private void applyRateLimit() throws LibraryException
    {
        if (this.blockWhileLimited)
        {
            L4J.getRateLimiter().get(L4J.getRegion()).acquire();
        } else
        {
            if (!L4J.getRateLimiter().get(L4J.getRegion()).tryAcquire())
            {
                throw new LibraryException(LibraryException.Type.RATE_LIMIT);
            }
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
                } catch (final UnsupportedEncodingException e)
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
     * @throws IOException
     * @throws Exception
     */
    public String doCall() throws IOException
    {
        final StringBuilder URL = new StringBuilder();
        if (!this.urlEndpoint.getValue().startsWith("http"))
        {
            URL.append("https://");
            if (!this.isToStatic())
            {
                URL.append(L4J.getRegion().getServer());
                try
                {
                    this.applyRateLimit();
                } catch (final LibraryException e)
                {
                    this.errorData = e;
                    return null;
                }
            } else
            {
                URL.append(Server.GLOBAL.getServer());
            }
            URL.append("/");
        }
        URL.append(this.replaceData(this.replaceVersion(this.replaceRegion(this.urlEndpoint.getValue()))));
        URL.append(this.getAPIkey()).append(this.getParameters());
        if (L4J.verbose || this.verbose)
        {
            DataCall.log.info(URL.toString());
        }
        final HttpURLConnection con = (HttpURLConnection) new URL(URL.toString()).openConnection();
        if (con.getResponseCode() != 200)
        {
            this.errorData = new LibraryException(con.getResponseCode(), con.getHeaderFieldInt("Retry-After", 0));
            return null;
        }
        final StringBuilder data = new StringBuilder();
        final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
        {
            data.append(inputLine);
        }
        in.close();
        return data.toString();
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
            } catch (final UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        return params.toString();
    }

    public boolean hasError()
    {
        return this.errorData != null;
    }

    private boolean isToStatic()
    {
        return this.urlEndpoint.toString().startsWith("api/lol/static-data");
    }

    private String replaceData(String s)
    {
        for (final String st : this.extraData.keySet())
        {
            if (this.extraData.get(st) != null)
            {
                s = s.replace("{" + st + "}", this.extraData.get(st).toString());
            }
        }
        return s.replace("{data}", this.buildData());
    }

    private String replaceRegion(final String s)
    {
        return s.replace("{region}", L4J.getRegion().name().toLowerCase());
    }

    private String replaceVersion(final String s)
    {
        return s.replace("{version}", VersionChecker.getFor(this.urlEndpoint));
    }
}
