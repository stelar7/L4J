package my.api.stelar7.usewith.lol.network;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;

public class HttpResponse
{

    String  body;
    Integer response;
    HttpURLConnection con;

    public HttpResponse(final HttpURLConnection con) throws Exception
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
        return con.getHeaderFieldInt("Retry-After", 10) * 1000;
    }
    
}
