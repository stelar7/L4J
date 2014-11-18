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
            return new HttpResponse(con);
        } catch (final Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}