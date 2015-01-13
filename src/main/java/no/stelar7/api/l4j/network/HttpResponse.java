package no.stelar7.api.l4j.network;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;

public class HttpResponse
{

    String            body;
    Integer           response;
    HttpURLConnection con;

    public HttpResponse(final HttpURLConnection con)
    {
        try
        {
            final StringBuilder sb = new StringBuilder();
            int x = -1;
            final Reader r = new InputStreamReader(con.getInputStream());
            while ((x = r.read()) > 0)
            {
                sb.append((char) x);
            }
            this.con = con;
            this.body = sb.toString();
            this.response = con.getResponseCode();
        } catch (Exception e)
        {
            this.body = null;
            this.response = 404;
            this.con = null;
        }
    }

    public String getBody()
    {
        return this.body;
    }

    public int getStatusCode()
    {
        return this.response;
    }

    public int getRetryAfter()
    {
        return con == null ? Integer.MAX_VALUE : con.getHeaderFieldInt("Retry-After", 10) * 1000;
    }

}
