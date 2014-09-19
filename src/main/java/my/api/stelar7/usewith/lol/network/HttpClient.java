package my.api.stelar7.usewith.lol.network;

import java.net.HttpURLConnection;
import java.net.URLConnection;

public class HttpClient
{
    public static HttpResponse execute(GET request)
    {
        try
        {
            final URLConnection uc = request.url.openConnection();
            return new HttpResponse((HttpURLConnection) uc);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
