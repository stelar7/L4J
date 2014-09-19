package my.api.stelar7.usewith.lol.network;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;

public class HttpResponse
{

    HttpURLConnection connection;
    String            body;
    Integer           response;

    public HttpResponse(HttpURLConnection con)
    {
        this.connection = con;
    }

    public int getStatusCode()
    {
        if (response != null) return response;
        try
        {
            return response = connection.getResponseCode();
        } catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public String getBody()
    {
        if (body != null) return body;
        try
        {
            StringBuilder sb = new StringBuilder();
            int x = -1;
            final Reader r = new InputStreamReader(connection.getInputStream());
            while ((x = r.read()) > 0)
            {
                sb.append((char) x);
            }
            return body = sb.toString();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
