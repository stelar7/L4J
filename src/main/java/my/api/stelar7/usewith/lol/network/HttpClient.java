package my.api.stelar7.usewith.lol.network;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URLConnection;

public class HttpClient
{
    public static HttpResponse execute(GET request, int count)
    {
        try
        {
            if (count > 3) return null;
            final URLConnection uc = request.url.openConnection();
            return new HttpResponse((HttpURLConnection) uc);
        } catch (Exception e)
        {
            if (e instanceof ConnectException)
            {
                try
                {
                    Thread.sleep(3000);
                    return execute(request, count + 1);
                } catch (InterruptedException e1)
                {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
            return null;
        }
    }

}