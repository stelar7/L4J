package my.api.stelar7.usewith.lol.network;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URLConnection;

public class HttpClient
{
    public static HttpResponse execute(final GET request, final int count)
    {
        try
        {
            if (count > 3) { return null; }
            final URLConnection uc = request.url.openConnection();
            return new HttpResponse((HttpURLConnection) uc);
        } catch (final Exception e)
        {
            if (e instanceof ConnectException)
            {
                try
                {
                    Thread.sleep(3000);
                    return HttpClient.execute(request, count + 1);
                } catch (final InterruptedException e1)
                {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
            return null;
        }
    }

}