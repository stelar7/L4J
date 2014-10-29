package my.api.stelar7.usewith.lol.network;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;

public class HttpResponse
{

    String  body;
    Integer response;

    public HttpResponse(HttpURLConnection con) throws Exception
    {
        StringBuilder sb = new StringBuilder();
        int x = -1;
        final Reader r = new InputStreamReader(con.getInputStream());
        while ((x = r.read()) > 0)
        {
            sb.append((char) x);
        }
        body = sb.toString();
        response = con.getResponseCode();
    }

    public int getStatusCode()
    {
        return response;
    }

    public String getBody()
    {
        return body;
    }

}
