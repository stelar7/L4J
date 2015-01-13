package no.stelar7.api.l4j.network;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;

public class HttpClient
{
    public static HttpResponse execute(final GET request)
    {
        try
        {
            URLConnection uc = request.url.openConnection();
            HttpURLConnection con = (HttpURLConnection) uc;
            return new HttpResponse(con);
        } catch (IOException e)
        {
            e.printStackTrace();
            return new HttpResponse(null);
        }
    }
}