package my.api.stelar7.usewith.lol.network;

import java.net.HttpURLConnection;
import java.net.URLConnection;

public class HttpClient
{
    public static HttpResponse execute(final GET request)
    {
        try
        {
            final URLConnection uc = request.url.openConnection();
            HttpURLConnection con = (HttpURLConnection) uc;
            if (con.getResponseCode() == 429)
            {
                Thread.sleep(con.getHeaderFieldInt("Retry-After", 10) * 1000);
                return execute(request);
            }
            return new HttpResponse(con);
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}