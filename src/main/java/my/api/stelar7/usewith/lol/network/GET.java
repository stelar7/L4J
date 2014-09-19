package my.api.stelar7.usewith.lol.network;

import java.net.MalformedURLException;
import java.net.URL;

public class GET
{

    URL url;

    public GET(String url)
    {
        try
        {
            this.url = new URL(url);
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

}
