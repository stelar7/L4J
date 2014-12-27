package no.stelar7.api.l4j.network;

import java.net.MalformedURLException;
import java.net.URL;

public class GET
{

    URL url;

    public GET(final String url)
    {
        try
        {
            this.url = new URL(url);
        } catch (final MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

}
